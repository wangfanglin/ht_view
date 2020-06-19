package com.dxt.ht.svc.form.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * 在线理赔表
 *
 * @author wangfanglin
 * @date 2020/04/10
 */
@Data
@ApiModel
public class HtClaimSettlementForm {

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "工单id")
    private String formId;

    @ApiModelProperty(value = "工单ID")
    private String workOrderId;

    @ApiModelProperty(value = "联系结果")
    private String contactStatus;

    @ApiModelProperty(value = "再次联系时间")
    private Date againContactDate;

    @ApiModelProperty(value = "客户名字")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "手机型号")
    private String phoneModel;

    @ApiModelProperty(value = "理赔资料中间表id")
    private String settlementDataId;

    @ApiModelProperty(value = "手机品牌")
    private String phoneBrand;

    @ApiModelProperty(value = "副部件")
    private String viceParts;

    @ApiModelProperty(value = "选择得主部件")
    private String masterUnit;

    @ApiModelProperty(value = "身份证")
    private String idNumber;

    @ApiModelProperty(value = "损坏原因")
    private String causeDamage;

    @ApiModelProperty(value = "维修类型")
    private String maintainId;

    @ApiModelProperty(value = "理赔资料是否合格 1是 0否")
    private String isQualified;

    @ApiModelProperty(value = "维修类型1维修0换新")
    private String maintainType;

    @ApiModelProperty(value = "服务方式0 寄修")
    private String serveType;

    @ApiModelProperty(value = "维修网点ID")
    private String maintainBranchId;

    @ApiModelProperty(value = "生成短信")
    private String sms;

    @ApiModelProperty(value = "是否需要快递1是0否")
    private String isExpress;

    @ApiModelProperty(value = "回寄姓名")
    private String returnName;

    @ApiModelProperty(value = "xxx")
    private String returnPhone;

    @ApiModelProperty(value = "城市编码")
    private String returnAreaCode;

    @ApiModelProperty(value = "回寄全地址")
    private String returnAddress;

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

    @ApiModelProperty(value = "状态0正常1删除2停用")
    private String status;

    @ApiModelProperty(value = "电话记录id")
    private String callInfoId;

    @ApiModelProperty(value = "理赔资料不合格原因")
    private String disqualificationDisqualification;

    @ApiModelProperty(value = "损坏图片")
    private String damageImgs;

    @ApiModelProperty(value = "身份证图片")
    private String identityCardImgs;

    @ApiModelProperty(value = "购买凭证图片")
    private String purchaseImgs;

}