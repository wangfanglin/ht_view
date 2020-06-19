package com.dxt.ht.svc.form.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 自付款信息表
 *
 * @author wangfanglin
 * @date 2020/04/14
 */
@Data
@ApiModel
public class HtDeductibleInfo {

    @ApiModelProperty(value = "自付款信息表id")
    private String id;

    @ApiModelProperty(value = "工单id")
    private String formId;

    @ApiModelProperty(value = "bdId")
    private String bdId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "自付款额度")
    private BigDecimal deductiblesLimit;

    @ApiModelProperty(value = "维修类型 同 工单类型")
    private String maintainTypr;

    @ApiModelProperty(value = "确认报价时间")
    private Date offerDate;

    @ApiModelProperty(value = "付款时间")
    private Date payDate;

    @ApiModelProperty(value = "支付状态")
    private String payStatus;

    @ApiModelProperty(value = "财务确认状态（0确认1待确认）")
    private String affirmStatus;

    @ApiModelProperty(value = "openId")
    private String openId;

    @ApiModelProperty(value = "微信昵称")
    private String wxNick;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "原因")
    private String remark;

    @ApiModelProperty(value = "支付方式")
    private String payType;

    @ApiModelProperty(value = "工单类型")
    private String formType;
}