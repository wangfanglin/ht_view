package com.dxt.ht.svc.form.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 维修工单-待联系客户表
 *
 * @author wangfanglin
 * @date 2020/04/09
 */
@Data
@ApiModel
public class HtRepairClientForm {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "工单id")
    private String formId;

    @ApiModelProperty(value = "联系情况")
    private String contactStatus;

    @ApiModelProperty(value = "再次沟通时间")
    private Date againDate;

    @ApiModelProperty(value = "理赔资料状态")
    private String claimStatus;

    @ApiModelProperty(value = "核实理赔资料")
    private String claimData;

    @ApiModelProperty(value = "手机品牌")
    private String phoneBrand;

    @ApiModelProperty(value = "理赔是否齐全  齐全1是 2否")
    private String isQualified;

    @ApiModelProperty(value = "手机型号")
    private String phoneType;

    @ApiModelProperty(value = "手机颜色")
    private String phoneColor;

    @ApiModelProperty(value = "维修是否完成，1未完成、2已完成")
    private String isEnd;

    @ApiModelProperty(value = "相关图片")
    private String image;

    @ApiModelProperty(value = "预期邮寄时间")
    private Date mailDate;

    @ApiModelProperty(value = "客户邮寄快递公司")
    private String expressCompany;

    @ApiModelProperty(value = "客户快递单号")
    private String expressNo;

    @ApiModelProperty(value = "状态（0正常1删除2停用）")
    private String status;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "备注原因")
    private String remarks;

    @ApiModelProperty(value = "维修完成时间")
    private Date repairEndDate;

    @ApiModelProperty(value = "其他收费")
    private BigDecimal otherPrice;

    @ApiModelProperty(value = "其他收费说明")
    private String otherRemarks;

    @ApiModelProperty(value = "损害部位相关图片")
    private String damageImg;

    @ApiModelProperty(value = "xxx")
    private BigDecimal manHourPrice;

    @ApiModelProperty(value = "自付费")
    private BigDecimal selfPrice;

    @ApiModelProperty(value = "本次总额")
    private BigDecimal sumPrice;

    @ApiModelProperty(value = "收款方式")
    private String receiptType;

    @ApiModelProperty(value = "是否全损 1全损 2非全损")
    private String isAll;

    @ApiModelProperty(value = "取设备方式")
    private String equipmentTake;

    @ApiModelProperty(value = "新机IMEI")
    private String newImei;

    @ApiModelProperty(value = "维修完成邮寄公司")
    private String repairExpressCompany;

    @ApiModelProperty(value = "维修完成邮寄单号")
    private String repairExpressNo;

    @ApiModelProperty(value = "维修完成快递日期")
    private Date repairExpressDate;

    @ApiModelProperty(value = "留存照片")
    private String repairEndImage;

    @ApiModelProperty(value = "上传的维修工单照片")
    private String uploadImage;

    @ApiModelProperty(value = "维修工单照片")
    private String repairFormImage;

    @ApiModelProperty(value = "修改维修时间次数")
    private Integer repairCount;

    @ApiModelProperty(value = "占位用来存电话录音")
    private String recording;

    @ApiModelProperty(value = "理赔资料")
    private String claimType;

    @ApiModelProperty(value = "操作原因/备注")
    private String reason;

    @ApiModelProperty(value = "关闭原因")
    private String closeType;

    @ApiModelProperty(value = "审核人")
    private String verifier;

    @ApiModelProperty(value = "重启原因")
    private String reopenType;

    @ApiModelProperty(value = "其他原因")
    private String otherReason;

    @ApiModelProperty(value = "操作类型 0改派 1关闭 2重开")
    private String operationType;

    @ApiModelProperty(value = "是否修改维修方案0否1是")
    private String isYes;

    @ApiModelProperty(value = "新发现损坏部位照片")
    private String newImage;

    @ApiModelProperty(value = "是否返修0否1是")
    private String isRepairBack;

}