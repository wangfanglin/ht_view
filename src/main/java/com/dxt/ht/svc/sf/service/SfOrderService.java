package com.dxt.ht.svc.sf.service;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.common.utils.ID;
import com.dxt.ht.svc.daddress.dao.HtMainPointMapper;
import com.dxt.ht.svc.daddress.entity.HtMainPoint;
import com.dxt.ht.svc.jaddress.dao.HtUserAddressDao;
import com.dxt.ht.svc.jaddress.entity.HtUserAddress;
import com.dxt.ht.svc.sf.PlaceOrderParameters;
import com.dxt.ht.svc.sf.PlaceOrderResult;
import com.dxt.ht.svc.sf.SfService;
import com.dxt.ht.svc.sf.dao.SfOrderDao;
import com.dxt.ht.svc.sf.entity.SaveSfOrderParameter;
import com.dxt.ht.svc.sf.entity.SfOrderInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@RestController
@Api(tags = "顺丰订单")
@RequestMapping("api/sf")
public class SfOrderService {
    @Autowired
    private SfOrderDao sfOrderDao;
    @Autowired
    private HtUserAddressDao htUserAddressDao;
    @Autowired
    private  HtMainPointMapper htMainPointMapper;
    @Autowired
    private SfService sfService;


    /**
     *保存顺丰下单信息
     * @param saveSfOrderParameter 顺丰下单参数
     * @return
     */
    @PostMapping("saveSfOrder")
    @ApiOperation(value = "新增顺丰订单", notes = "新增顺丰订单方法")
    public ApiResult saveSfOrder(@RequestBody @Valid SaveSfOrderParameter saveSfOrderParameter) {
        SfOrderInfo sfOrderInfo = new SfOrderInfo();
        HtUserAddress userAddress = htUserAddressDao.singleUserAddress(Integer.parseInt(saveSfOrderParameter.getAddressId()), saveSfOrderParameter.getUserId());
        if (userAddress == null) {
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA, "找不到寄件人地址信息");
        }
        sfOrderInfo.setId(saveSfOrderParameter.getId());
        sfOrderInfo.setOrderId(saveSfOrderParameter.getOrderId());
        sfOrderInfo.setjContact(userAddress.getUserName());
        sfOrderInfo.setjMobile(userAddress.getUserPhone());
        sfOrderInfo.setjProvince(userAddress.getProvinceCode());
        sfOrderInfo.setjCity(userAddress.getCityCode());
        sfOrderInfo.setjArea(userAddress.getAreaCode());
        HtMainPoint type = htMainPointMapper.findType(saveSfOrderParameter.getOrderId());
        if (type.getFormType().equals("1")) {
            sfOrderInfo.setdTel("18511019009");
            sfOrderInfo.setdContact("焦金辉");
            sfOrderInfo.setdProvince("北京市");
            sfOrderInfo.setdCity("北京市");
            sfOrderInfo.setdArea("朝阳区");
            sfOrderInfo.setdCompany("北京市朝阳区霄云路36号国航大厦1303室");
        }
        if (type.getFormType().equals("2")) {
            HtMainPoint postAddress = htMainPointMapper.findPostAddress(saveSfOrderParameter.getOrderId());
            if (postAddress == null) {
                return ApiResultFactory.newInstance(ResultStatus.NONE_DATA, "找不到收件人地址信息");
            }
            sfOrderInfo.setdCompany(postAddress.getMaintenancePointName());
            sfOrderInfo.setdContact(postAddress.getContactOne());
            sfOrderInfo.setdTel(postAddress.getContactNumberOne());
            sfOrderInfo.setdProvince(postAddress.getProvince());
            sfOrderInfo.setdCity(postAddress.getCity());
            sfOrderInfo.setdArea(postAddress.getArea());
        }
            StrBuilder strBuilder = new StrBuilder("SF");
            strBuilder.append(ID.getId()).append(saveSfOrderParameter.getOrderId());
            sfOrderInfo.setSfOrderId(strBuilder.toString());
            StrBuilder strBuilderJaddress = new StrBuilder();
            strBuilderJaddress.append(userAddress.getProvinceCode()).append(userAddress.getCityCode()).append(userAddress.getAreaCode()).append(userAddress.getAddress());
            sfOrderInfo.setjAddress(strBuilderJaddress.toString());
            StrBuilder strBuilderDaddress = new StrBuilder();
            strBuilderDaddress.append(sfOrderInfo.getdProvince()).append(sfOrderInfo.getdCity()).append(sfOrderInfo.getdArea()).append(sfOrderInfo.getdAddress());
            sfOrderInfo.setdAddress(strBuilderDaddress.toString());
            PlaceOrderParameters placeOrderParameters = new PlaceOrderParameters(sfOrderInfo.getSfOrderId(), sfOrderInfo.getjContact(), sfOrderInfo.getjMobile(), sfOrderInfo.getjAddress(), sfOrderInfo.getdCompany(), sfOrderInfo.getdContact(), sfOrderInfo.getdTel(), sfOrderInfo.getdAddress(), "1", saveSfOrderParameter.getDate());
            PlaceOrderResult placeOrderResult = sfService.placeOrder(placeOrderParameters);
            if (placeOrderResult.getStatus().isSuccess()) {
                sfOrderInfo.setMailNo(placeOrderResult.getMailNo());
                SfOrderInfo sfOrder = sfOrderDao.logisticsInformation(sfOrderInfo.getOrderId());
                if (sfOrder != null && StringUtils.isNotBlank(sfOrder.getMailNo())) {
                    return ApiResultFactory.newInstance(ResultStatus.NONE_CHANGE, "订单已存在");
                } else {
                    int resultSave = sfOrderDao.updateByPrimaryKeySelective(sfOrderInfo);
                    if (resultSave == 0) {
                        return ApiResultFactory.newInstance(ResultStatus.NONE_CHANGE, "增加无效");
                    }
                    return ApiResultFactory.newInstance(ResultStatus.SUCCESS,"下单成功");
                }
            }
            return ApiResultFactory.newInstance(ResultStatus.INTERFACE_FALSE);

    }

    /**
     * 查找收寄地址
     * @param mailNo 顺丰单号
     * @return 收件人所在地址与寄件人所在地址
     */
    @GetMapping("findAddress")
    @ApiOperation(value = "查找收寄地址", notes = "查找收寄地址方法")
    @ApiImplicitParam(name="mailNo",value ="顺丰运单号",dataType = "String",paramType = "query")
    public ApiResult<SfOrderInfo> findAddress(String mailNo) {
        if(mailNo.isEmpty()){
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM,"无效的顺丰运单号");

        }
        SfOrderInfo sfOrderInfo=sfOrderDao.findAddress(mailNo);
        if(sfOrderInfo!=null) {
            return ApiResultFactory.getSuccess(sfOrderInfo);
        }
        return  ApiResultFactory.newInstance(ResultStatus.NONE_DATA);

    }

    /**
     *查询物流详情
     * @param orderId 订单号
     * @return 物流信息
     */
    @GetMapping("logisticsInformation")
    @ApiOperation(value = "查询物流详情", notes = "查询物流详情方法")
    @ApiImplicitParam(name="orderId",value ="订单号",dataType = "String",paramType = "query")
    public ApiResult<Map<String,Object>> logisticsInformation(String orderId){
        if(ObjectUtils.isEmpty(orderId)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"查询数据为空");
        }
        SfOrderInfo sfOrderInfo = sfOrderDao.logisticsInformation(orderId);
        if(ObjectUtils.isEmpty(sfOrderInfo)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"查询数据为空");
        }
       else {
            if (StringUtils.isBlank(sfOrderInfo.getSfOrderId())) {
                return ApiResultFactory.newInstance(ResultStatus.NONE_DATA, "没有找到顺风单号");
            }
            if (StringUtils.isBlank(sfOrderInfo.getMailNo())) {
                return ApiResultFactory.newInstance(ResultStatus.NONE_DATA, "没有找到顺风运单号");
            }
            Map<String,Object> result= sfService.findRouting(sfOrderInfo.getSfOrderId(),sfOrderInfo.getMailNo());
            if(result != null){
                return ApiResultFactory.getSuccess(result);
            }
        }
        return ApiResultFactory.newInstance(ResultStatus.INTERFACE_FALSE);

    }

}
