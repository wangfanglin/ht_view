package com.dxt.ht.svc.wx.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class WxPayParam {

    @ApiModelProperty(value = "客户端IP",required=true)
    @NotNull(message = "客户端IP地址不能为空")
    @NotEmpty(message = "客户端IP地址不能为空")
    private String spbillCreateIp;

    @ApiModelProperty(value = "支付金额",required=true)
    @NotNull(message = "支付金额不能为空")
    @NotEmpty(message = "支付金额不能为空")
    private String totalFee;

    @ApiModelProperty(value = "自付款单号",required=true)
    @NotNull(message = "自付款单号不能为空")
    @NotEmpty(message = "自付款单号不能为空")
    private String outTradeNo;

    @ApiModelProperty(value = "工单id",required=true)
    @NotNull(message = "工单id不能为空")
    @NotEmpty(message = "工单id不能为空")
    private String formId;

    @ApiModelProperty(value = "openid",required=true)
    @NotNull(message = "openid不能为空")
    @NotEmpty(message = "openid不能为空")
    private String openid;


}
