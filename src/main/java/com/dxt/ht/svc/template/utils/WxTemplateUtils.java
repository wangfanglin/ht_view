package com.dxt.ht.svc.template.utils;

import com.alibaba.fastjson.JSONObject;
import com.dxt.ht.svc.common.utils.HttpReqUtils;
import com.dxt.ht.svc.template.dao.WxTemplateMapper;
import com.dxt.ht.svc.template.entity.WxTemplate;
import com.dxt.ht.svc.wx.Constant;
import com.dxt.ht.svc.wx.WeixinUtil;
import com.dxt.pass.util.GsonUtils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信模板服务
 *
 * @author wangfanglin
 * @date 2020/04/08
 */
@Service
public class WxTemplateUtils {

    @Autowired
    private WxTemplateMapper wxTemplateMapper;

    @Autowired
    private WeixinUtil weixinUtil;

    /** 保修成功 */
    private static final  String BXCG = "BXCG";

    /** 资料不合格 */
    private static final  String ZLBHG = "ZLBHG";

    /** 派单通知(顺非、非顺非) */
    private static final  String PDTZ = "PDTZ";

    /** 改派通知 */
    private static final  String GPWXTZ = "GPWXTZ";

    /** 付款通知 */
    private static final  String DFKTZ = "DFKTZ";

    /** 维修报告通知 */
    private static final  String WXBGTZ = "WXBGTZ";

    /** 满意度调查 */
    private static final  String MYDDC = "MYDDC";

    /**
     * status ：保修成功
     * 通知名称：申请成功通知
     *
     * @param applicationDate 申请时间
     * @param formNumber 保单号
     * @param imei imei
     * @param userId 发送的用户
     * @return
     * @throws Exception
     */
    public String sendWxTemplateStatusOne(String applicationDate,String formNumber,String imei,String userId) throws Exception {
        String token = HttpReqUtils.getWxToken();
        Map<String,String> paraMap = putMapParams(formNumber,imei,applicationDate);
        return sendMsg(userId,token,null,BXCG,paraMap);
    }

    /**
     * status ：资料不合格
     * 通知名称：更新资料通知
     *
     * @param account 账户
     * @param accountName 姓名
     * @param userId 发送的用户
     * @return
     * @throws Exception
     */
    public  String sendWxTemplateStatusTwo(String account,String accountName,String userId) throws Exception {
        String token = HttpReqUtils.getWxToken();
        Map<String,String> paraMap = putMapParams(account,accountName);
        return sendMsg(userId,token,null,ZLBHG,paraMap);
    }

    /**
     * status ：派单通知  （非顺丰、非顺非）
     * 通知名称：派单邮寄通知
     *
     * @param master 联系人
     * @param address 地点
     * @param phone 联系方式
     * @param workContent 工作内容
     * @param date 时间
     * @param userId 发送的用户
     * @return
     * @throws Exception
     */
    public  String sendWxTemplateStatusThree(String master,String address,String phone,String workContent,String date,String userId)throws Exception {
        String token = HttpReqUtils.getWxToken();
        Map<String,String> paraMap = putMapParams(address,workContent,master,phone,date);
        return sendMsg(userId,token,null,PDTZ,paraMap);
    }

    /**
     * status ：改派通知
     * 通知名称：改派维修通知
     *
     * @param formNumber 工单号
     * @param date 时间
     * @param userId 发送的用户
     * @return
     * @throws Exception
     */
    public String sendWxTemplateStatusFive(String formNumber,String date,String userId) throws Exception {
        String token = HttpReqUtils.getWxToken();
        Map<String,String> paraMap = putMapParams(formNumber,date);
        return sendMsg(userId,token,null,GPWXTZ,paraMap);
    }

    /**
     * status ：待付款通知
     * 通知名称：付款通知
     *
     * @param url 详情链接
     * @param demandOrder 需求单号
     * @param date 时间
     * @param userId 发送的用户
     * @return
     * @throws Exception
     */
    public  String sendWxTemplateStatusSix(String url,String demandOrder,String date,String userId) throws Exception {
        String token = HttpReqUtils.getWxToken();
        Map<String,String> paraMap = putMapParams(demandOrder,date);
        return sendMsg(userId,token,null,DFKTZ,paraMap);
    }


    /**
     * status：维修报告通知
     * 通知名称：维修报告
     *
     * @param repairMan 报修人
     * @param equipment 设备信息
     * @param date 维修完成时间
     * @param userId 发送的用户
     * @return
     * @throws Exception
     */
    public  String sendWxTemplateStatusSeven(String repairMan,String equipment,String date,String userId) throws Exception {
        String token = HttpReqUtils.getWxToken();
        Map<String,String> paraMap = putMapParams(repairMan,equipment,date);
        return sendMsg(userId,token,null,WXBGTZ,paraMap);
    }

    /**
     * status：维修完成，满意度回访
     * 通知名称：满意度调查
     *
     * @param serviceContent 服务内容
     * @param ServicePersonnel 服务人员
     * @param userId 发送的用户
     * @return
     * @throws Exception
     */
    public String sendWxTemplateStatusEight(String serviceContent,String ServicePersonnel,String userId) throws Exception{
        String token = HttpReqUtils.getWxToken();
        Map<String,String> paraMap = putMapParams(serviceContent,ServicePersonnel);
        return sendMsg(userId,token,null,MYDDC,paraMap);
    }


    /**
     * 调用微信模版 发送
     * @param toUser            用户的openId
     * @param accessToken       微信的token
     * @param url               模版点击要跳转的URL
     * @param templateType      模版id
     * @param paraMap           参数
     * @return
     */
    public String sendMsg(String toUser, String accessToken, String url, String templateType, Map<String, String> paraMap) {
        //根据type值从模板消息表中查找
        WxTemplate weixinTemplate = new WxTemplate();
        Map<String,String> params = new HashMap<>();
        params.put("templateType",templateType);
        List<WxTemplate> wxTemplateList = wxTemplateMapper.select(params);
        String resultMsg = "";
        for (WxTemplate wxTemplate : wxTemplateList) {
            String content = wxTemplate.getContent();
            String sendContent = wxTemplate.getSendContent();
            String firstTitle = wxTemplate.getHeadText();
            String remarkTitle = wxTemplate.getEndText();
            String template_id = wxTemplate.getTemplateId();
            firstTitle.replace("&lt;", "<").replace("&gt;", ">");
            remarkTitle=remarkTitle.replace("&lt;", "<").replace("&gt;", ">").replace("|||", "\n");
            //替换头和尾的内容
            for (String keyStr : paraMap.keySet()) {
                String newfirstTitleStr = new String();
                String newremarkTitleStr = new String();
                newfirstTitleStr = firstTitle.replace("{"+keyStr+"}", paraMap.get(keyStr));
                newremarkTitleStr = remarkTitle.replace("{"+keyStr+"}", paraMap.get(keyStr));
                firstTitle = newfirstTitleStr;
                remarkTitle = newremarkTitleStr;
            }
            //替换消息模板内容
            for (String keyStr : paraMap.keySet()) {
                if(!"first".equals(keyStr) && !"remark".equals(keyStr)){
                    String newStr = new String();
                    newStr = sendContent.replace("{{"+keyStr+".DATA}}", paraMap.get(keyStr));
                    sendContent = newStr;
                }
            }
            String firstStr = sendContent.replace("{{first.DATA}}", firstTitle);
            String remarkStr = firstStr.replace("{{remark.DATA}}", remarkTitle);
            sendContent = remarkStr;
            resultMsg += sendTemplateMessage(toUser, template_id, url, sendContent, accessToken);
        }
        return resultMsg;


    }

    private String sendTemplateMessage(String touser, String template_id, String url, String data,String accessToken) {
        String sendUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
        Map<String, String> resultMap = Maps.newHashMap();
        if(StringUtils.isBlank(data)||StringUtils.isBlank(touser)||StringUtils.isBlank(template_id)){
            resultMap.put("msg", "参数错误，请检查参数是否正确！");
            resultMap.put("status","error");
        }else{
            String resultStr = "";
            try {
                data = URLDecoder.decode(data, "UTF-8");
                url = url==null?"":URLDecoder.decode(url, "UTF-8");
                JSONObject dataJson = new JSONObject();
                dataJson.put("touser", touser);
                dataJson.put("template_id", template_id);
                dataJson.put("url", url);
                dataJson.put("data", JSONObject.parse(data));
                resultStr = HttpReqUtils.sendPost(sendUrl, dataJson.toString());
                JSONObject resultJson =JSONObject.parseObject(resultStr);
                String errmsg = (String) resultJson.get("errmsg");
                //如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
                if(!"ok".equals(errmsg)){
                    resultMap.put("msg", "发送失败！");
                    resultMap.put("status", "error");
                    resultMap.put("resultStr", resultStr);
                }else{
                    resultMap.put("msg", "发送成功！");
                    resultMap.put("status", "success");
                    resultMap.put("resultStr", resultStr);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("msg", "参数错误，请检查参数是否正确！");
                resultMap.put("status", "error");
                resultMap.put("resultStr", resultStr);
            }
        }
        return GsonUtils.getGson().toJson(resultMap);
    }

    /**
     * map插入方法（String）
     * @param arg
     * @return
     */
    public static Map<String, String> putMapParams(String ... arg){
        Map<String,String> paraMap = new HashMap<>();
        for(int i=0; i<arg.length;i++){
            String keywordi = "keyword"+(i+1);
            paraMap.put(keywordi,arg[0]);
        }
        return paraMap;
    }

}
