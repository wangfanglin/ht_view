package com.dxt.ht.svc.policy.entity;

import com.dxt.ht.svc.form.entity.HtClaimDisqualificationLog;
import com.dxt.ht.svc.form.entity.HtFormInfo;
import com.dxt.ht.svc.form.entity.HtReceiptData;
import com.dxt.ht.svc.product.entity.HtBrandInfo;
import com.dxt.ht.svc.product.entity.HtChannelProductInfo;
import com.dxt.ht.svc.product.entity.HtPhoneModelInfo;
import com.dxt.ht.svc.product.entity.HtProductInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel
public class HtPolicyInfo {

    @ApiModelProperty(value = "保单id")
    private String id;

    @ApiModelProperty(value = "唯一标识暂时无用")
    private String uniqueMark;

    @ApiModelProperty(value = "客户姓名")
    private String strName;

    @ApiModelProperty(value = "联系方式")
    private String strContactNum;

    @ApiModelProperty(value = "证件类型")
    private String strType;

    @ApiModelProperty(value = "微信")
    private String strWechat;

    @ApiModelProperty(value = "证件号码")
    private String strCardId;

    @ApiModelProperty(value = "生日")
    private String dateBirthday;

    @ApiModelProperty(value = "性别1男2女")
    private String strSex;

    @ApiModelProperty(value = "分公司")
    private String strFiliale;

    @ApiModelProperty(value = "渠道名称")
    private String strChannelName;

    @ApiModelProperty(value = "渠道Id")
    private String channelId;

    @ApiModelProperty(value = "渠道产品名称")
    private String channelProductName;

    @ApiModelProperty(value = "产品售价")
    private Integer intProductPrice;

    @ApiModelProperty(value = "保额")
    private BigDecimal coverage;

    @ApiModelProperty(value = "手机价格")
    private Integer intSellPrice;

    @ApiModelProperty(value = "购机时间")
    private String dateCostTime;

    @ApiModelProperty(value = "省份")
    private String strProvince;

    @ApiModelProperty(value = "卡号")
    private String strCardNumber;

    @ApiModelProperty(value = "购卡时间")
    private String dateBuyCard;

    @ApiModelProperty(value = "保单生效时间")
    private String dateEffectiveDate;

    @ApiModelProperty(value = "手机购买方式")
    private String strBuyPattern;

    @ApiModelProperty(value = "保单终止时间")
    private String dateEndDate;

    @ApiModelProperty(value = "制式")
    private String strSys;

    @ApiModelProperty(value = "手机品牌")
    private String strPhoneBrand;

    @ApiModelProperty(value = "颜色")
    private String strColor;

    @ApiModelProperty(value = "手机型号")
    private String strPhoneModel;

    @ApiModelProperty(value = "城市")
    private String strCity;

    @ApiModelProperty(value = "内存")
    private Integer intInternal;

    @ApiModelProperty(value = "门店")
    private String strStore;

    @ApiModelProperty(value = "手机IMEI号")
    private String strImeiNum;

    @ApiModelProperty(value = "销售员")
    private String strSalesman;

    @ApiModelProperty(value = "贷款额度")
    private Integer intLoanAmount;

    @ApiModelProperty(value = "批次号")
    private Integer intBatchNum;

    @ApiModelProperty(value = "保费收取状态")
    private Integer intStatus;

    @ApiModelProperty(value = "产品名称")
    private String strCardType;

    @ApiModelProperty(value = "渠道产品关联ID")
    private String channelProductId;

    @ApiModelProperty(value = "保单提交时间")
    private String policySubmissionDate;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    private String updateDate;

    @ApiModelProperty(value = "状态：0正常1删除2停用")
    private String status;

    @ApiModelProperty(value = "原因")
    private String remarks;

    @ApiModelProperty(value = "0不删除1删除")
    private String delflag;

    @ApiModelProperty(value = "小程序创建保单填写的 邀请人工号")
    private String invite;

    @ApiModelProperty(value = "是否需要短信发送邀请码，1发送，2客户要求不发送")
    private String inviteflag;

    @ApiModelProperty(value = "保单来源，1为客户端")
    private String fromtype;

    @ApiModelProperty(value = "xxxx")
    private Integer bhstauts;

    @ApiModelProperty(value = "是否联系成功顾客")
    private String callguke;

    @ApiModelProperty(value = "微信openId")
    private String openid;

    @ApiModelProperty(value = "设备号")
    private String externalIdentifier;

    @ApiModelProperty(value = "渤海取消类型")
    private String bhFlag;

    @ApiModelProperty(value = "甜橙延保期数")
    private String insurancePeriods;

    @ApiModelProperty(value = "保单剩余限额")
    private BigDecimal surplusCoverage;

    /** to see policyStatus */
    @Deprecated
    @ApiModelProperty(value = "保单流程状态：0保障中1待生效2已出险服务终止")
    private String processState;

    private String userId;

    @ApiModelProperty(value = "保单状态：0待生效1保障中2已失效")
    private String policyStatus;
    private String applyStatus;

    private HtPolicyDetail htPolicyDetail;
    private HtPhoneModelInfo htPhoneModelInfo;
    private HtBrandInfo htBrandInfo;
    private HtFormInfo htFormInfo;
    private HtChannelProductInfo htChannelProductInfo;
    private HtReceiptData htReceiptData;
    private HtClaimDisqualificationLog htClaimDisqualificationLog;

}