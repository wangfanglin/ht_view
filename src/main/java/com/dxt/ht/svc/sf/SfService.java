package com.dxt.ht.svc.sf;

import com.dxt.ht.svc.sf.service.SfOrderInfoService;
import com.sf.csim.express.service.CallExpressServiceTools;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Map;

import java.io.IOException;
import java.util.*;

@Service
public class SfService {

    @Autowired
    SfOrderInfoService sfOrderInfoService;

    private static final String clientCode = "HDXTBJKJ";                                //此处替换为您在丰桥平台获取的顾客编码
    private static final String checkword = "BEi7rB2wD53NlMyDoJb9gO8wdMY9B8OT";     //此处替换为您在丰桥平台获取的校验码
    private static final String reqURL="https://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";

    //路由查询报文
    private static final String routing= "<Request service='RouteService' lang='zh-CN'>\n" +
            "<Head>HEAD</Head>\n" +
            "<Body>\n" +
            "<RouteRequest\n" +
            "tracking_type='2'\n" +
            "method_type='1'\n" +
            "tracking_number='TRACKING_NUMBER'/>\n" +
            "</Body>\n" +
            "</Request>";



    /**
     * 顺丰下单接口
     * @param parameters    顺丰下单接口入参
     * @return              顺丰下单接口返回值
     * @throws DocumentException        如果解析顺丰返回的xml异常，则会 throws 该异常
     */
    public PlaceOrderResult placeOrder(PlaceOrderParameters parameters)  {
        String myReqXML=SfXmlUtils.getPlaceOrderParameter(clientCode,parameters);
        String respXml= CallExpressServiceTools.callSfExpressServiceByCSIM(reqURL, myReqXML, clientCode, checkword);
        //需要实现该方法
        sfOrderInfoService.insertSfLog(parameters.getOrderId(),myReqXML,respXml);
        Map<String,Object> result;
        try {
            result = XmlUtils.xml2mapWithAttr(respXml,true);
        } catch (Exception e) {
            e.printStackTrace();
            return new PlaceOrderResult(PlaceOrderResultStatus.XML_ERROR);
        }


        return new PlaceOrderResult(result);
    }

    /***
     *  顺丰下单接口是否成功
     * @param placeOrderResult  顺丰下单接口返回值（placeOrder的返回值）
     * @return  true：下单成功       false：下单失败
     *
     */
    public static PlaceOrderResultStatus checkPlaceOrder(Map<String,Object> placeOrderResult){
        try {
            //如果顺丰下单接口返回值，直接返回 返回值为空
            if(placeOrderResult==null) {
                return PlaceOrderResultStatus.RESULT_NULL;
            }


            Map<String,Object> response = (Map<String, Object>) placeOrderResult.get("Response");
            String head = (String) response.get("Head");


            //调用 检查 HEAD 的方法，如果不是success 直接返回错误信息
            PlaceOrderResultStatus isHead = checkHead(response, head);
            if (!isHead.isSuccess()) {
                return isHead;
            }


            Map<String,Object> body = (Map<String, Object>) response.get("Body");
            Map<String,Object> orderResponse = (Map<String, Object>) body.get("OrderResponse");
            //筛单结果:  1:人工确认 2:可收派 3:不可以收派
            String filterResult = (String) orderResponse.get("@filter_result");
            //不可以收派的原因代码:  1:收方超范围 2:派方超范围 3:其它原因
            String remark = (String) orderResponse.get("@remark");


            //调用 检查是否可以派收的方法，如果不是success 直接返回错误信息
            PlaceOrderResultStatus isAccept = checkAccept(filterResult,remark);
            if (!isAccept.isSuccess()) {
                return isAccept;
            }

            //调用 检查 服务返回是否正常 ，如果不是success 直接返回错误信息
            if (!checkServer(orderResponse)) {
                return PlaceOrderResultStatus.SERVER_ERROR;
            }


            return PlaceOrderResultStatus.SUCCESS;

        }catch (Exception e){
            return PlaceOrderResultStatus.OTHER_ERROR;
        }
    }



    /**
     * 路由查询
     * @param sfOrderId
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public  Map<String,Object> findRouting(String sfOrderId,String mailNo) {
        String reqXml = routing;
        String myReqXML = reqXml.replace("TRACKING_NUMBER", sfOrderId).replace("HEAD",clientCode);

        System.out.println("请求报文：" + myReqXML);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(reqURL, myReqXML, clientCode, checkword);
        Map<String,Object> result = null;
        try {
            result = analysisResult(respXml, mailNo);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 解析 返回的 路由信息
     * @param xml           丰桥接口返回 xml 信息
     * @param mailNo        运单号，注意是顺丰返回的运单号，而不是订单号
     * @return              返回信息
     *                      status代表当前的状态
     *                          OK：成功
     *                          ORDER_NOT_FOUND：订单没有找到
     *                          其他：丰桥返回的错误
     *                      order代表丰桥返回的订单路由信息
     */
    public static Map<String,Object> analysisResult(String xml, String mailNo) throws DocumentException {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> xmlMap =XmlUtils.xml2mapWithAttr(xml,false);
        String head = (String) xmlMap.get("Head");
        Map<String,Object> body = (Map<String, Object>) xmlMap.get("Body");

        //如果顺丰接口返回错误，那么直接返回错误信息
        if(!"OK".equals(head)){
            result.put("status",head);
            return result;
        }
        //订单（包活运单号和路由单号）
        Map<String,Object> order;

        //如果接口返回了多个订单，找到运单号对应的进行展示
        order = orderConversion(mailNo, body);

        //如果订单信息为空，则直接返回 ORDER_NOT_FOUND，即订单没有找到
        if(order==null){
            result.put("status","ORDER_NOT_FOUND");
            return result;
        }
        //整理数据接口，当Route只有一个时，也将其转为List进行输出
        routeConversion(order);


        result.put("status","OK");
        result.put("order",order);
        return result;
    }



    /**
     * 整理数据接口，当Route只有一个时，也将其转为List进行输出
     * @param order 订单信息
     */
    private static void routeConversion(Map<String, Object> order) {
        if(order.get("Route") instanceof Map){
            List<Map<String,Object>> list = new ArrayList<>();
            list.add((Map<String, Object>) order.get("Route"));
            order.put("Route",list);
        }
    }



    /**
     *  整理订单数据
     *  当返回两个时，选择运单号对应的那一个
     * @param mailNo    运单号
     * @param body      接口返回的body数据
     * @return          如果返回空，代表并没有符合的数据，否则则返回对应的订单数据
     */
    private static Map<String, Object> orderConversion(String mailNo, Map<String, Object> body) {
        Map<String, Object> order = null;
        if(body.get("RouteResponse") instanceof List){
            for (Map<String,Object> o:
                    (List<Map<String,Object>>)body.get("RouteResponse") ) {
                if(mailNo.equals( o.get("@mailNo"))){
                    order = o;
                }
            }
        }else{
            order = (Map<String, Object>) body.get("RouteResponse");
        }
        return order;
    }





    /**
     * 检查 服务返回是否正常
     * 判断依据为 返回值的 invoke_result 是否为 OK
     * 如果是 OK 返回 成功，
     * 如果不是，则返回相应错误信息
     *
     * 该判读主要的错误原因是 数据异常 和 重复下单
     * @param orderResponse
     * @return
     */
    private static boolean checkServer(Map<String, Object> orderResponse) {
        Map<String,Object> rlsInfo = (Map<String, Object>) orderResponse.get("rls_info");
        String invokeResult = (String) rlsInfo.get("@invoke_result");
        if(!"OK".equals(invokeResult)){
            return false;
        }
        return true;
    }


    /**
     * 检查 检查是否可以派收
     * 如果是 filterResult 是 "2" 返回 成功，
     * 如果不是，则返回相应错误信息
     *
     * @param filterResult      筛单结果:  1:人工确认 2:可收派 3:不可以收派
     * @param remark            不可以收派的原因代码:  1:收方超范围 2:派方超范围 3:其它原因
     *
     * @return                  如果不是PlaceOrderResultStatus.SUCCESS 则该检查没有通过
     */
    private static PlaceOrderResultStatus  checkAccept(String filterResult,String remark) {
        if("3".equals(filterResult)){
            if ("1".equals(remark)) return PlaceOrderResultStatus.OUT_OF_RECEIVE_ADDRESS;
            else if ("2".equals(remark)) return PlaceOrderResultStatus.OUT_OF_SEND_ADDRESS;
            else if ("3".equals(remark)) return PlaceOrderResultStatus.OUT_OF_OTHER_ADDRESS;
            else return PlaceOrderResultStatus.OUT_OF_DEFAULT_ADDRESS;
        }
        //如果返回人工确认 通知用户 需要人工确认
        if("1".equals(filterResult)){
            return PlaceOrderResultStatus.ARTIFICIAL_CONFIRM;
        }
        return PlaceOrderResultStatus.SUCCESS;
    }

    /**
     * 检查 返回值的 HEAD是否为 OK
     * 如果是 OK 返回 成功，
     * 如果不是，则返回相应错误信息
     *
     * 该判读主要的错误原因是 数据异常 和 重复下单
     * @param response  顺丰返回值中的 response
     * @param head      顺丰返回值中的 head值
     *
     * @return          如果不是PlaceOrderResultStatus.SUCCESS 则该检查没有通过
     */
    private static PlaceOrderResultStatus checkHead(Map<String, Object> response, String head) {
        //只有在head==OK的情况，才算成功
        if(!"OK".equals(head)){
            Map<String,Object> error = (Map<String, Object>) response.get("ERROR");
            String code = (String) error.get("@code");
            return PlaceOrderResultStatus.getStatusByCode(code);
        }
        return PlaceOrderResultStatus.SUCCESS;
    }




    /**
     * 下单返回状态
     */
    public enum  PlaceOrderResultStatus{
        SUCCESS(true,"1000000","下单成功"),
        RESULT_NULL(false,"1000001","返回值为空"),
        DATA_ERROR(false,"4001","系统发生数据错误或运行时异常(大多情况是因为收货地址或寄货地址不一致导致的)"),
        REPEAT_ORDER(false,"8016","重复下单"),
        CUSTID_ERROR(false,"8114","传入了不可发货的月结卡号"),
        OTHER_ERROR(false,"4444","其他错误"),
        OUT_OF_RECEIVE_ADDRESS(false,"2000001","收方超范围"),
        OUT_OF_SEND_ADDRESS(false,"2000002","派方超范围"),
        OUT_OF_OTHER_ADDRESS(false,"2000003","其它原因导致不可派送"),
        OUT_OF_DEFAULT_ADDRESS(false,"2000004","其它原因导致不可派送"),
        ARTIFICIAL_CONFIRM(false,"2000005","需要人工确认"),
        SERVER_ERROR(false,"3000001","接口参数异常"),
        XML_ERROR(false,"4000001","xml解析错误");

        /**
         * 状态
         */
        private boolean status;
        /**
         * code
         */
        private String code;
        /**
         * 信息
         */
        private String message;

        PlaceOrderResultStatus(boolean status, String code, String message) {
            this.status = status;
            this.code = code;
            this.message = message;
        }

        public boolean isStatus() {
            return status;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
        public boolean isSuccess(){
            return this==SUCCESS;
        }

        @Override
        public String toString() {
            return "PlaceOrderResultStatus{" +
                    "status=" + status +
                    ", code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }

        public static PlaceOrderResultStatus getStatusByCode(final String code){
            return Arrays.stream(PlaceOrderResultStatus.values())
                    .filter(status -> status.getCode().equals(code))
                    .findFirst()
                    .orElse(PlaceOrderResultStatus.OTHER_ERROR);
        }
    }
}
