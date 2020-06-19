package com.dxt.ht.svc.apply.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户申请表
 *
 * @author 王芳林
 * @date 2020/04/09
 */
@ApiModel
@Data
public class HtUserApplyInfo {

    @ApiModelProperty(value = "用户申请表id")
    private String id;

    @ApiModelProperty(value = "保单id",required = true)
    @NotNull(message = "保单id不能为空")
    @NotEmpty(message = "保单id不能为空")
    private String policyId;

    @ApiModelProperty(value = "工单id")
    private String formId;

    @ApiModelProperty(value = "手机品牌",required = true)
    private String facilityBrandId;

    @ApiModelProperty(value = "手机型号",required = true)
    private String facilityModelId;

    @ApiModelProperty(value = "手机颜色",required = true)
    private String facilityColor;

    @ApiModelProperty(value = "地址")
    private String areaId;

    @ApiModelProperty(value = "故障发生时间",required = true)
    @NotNull(message = "故障发生时间不能为空")
    @NotEmpty(message = "故障发生时间不能为空")
    private String malfunctionDate;

    @ApiModelProperty(value = "故障原因",required = true)
    @NotNull(message = "故障原因不能为空")
    @NotEmpty(message = "故障原因不能为空")
    private String malfunctionId;

    @ApiModelProperty(value = "用户手机号",required = true)
    @NotEmpty(message = "用户手机号不能为空")
    private String userPhone;

    @ApiModelProperty(value = "身份姓名",required = true)
    @NotEmpty(message = "身份姓名不能为空")
    private String cardName;

    @ApiModelProperty(value = "身份证号",required = true)
    @NotEmpty(message = "身份证号不能为空")
    private String cardId;

    @ApiModelProperty(value = "身份证开始时间",required = true)
    @NotNull(message = "身份证开始时间不能为空")
    @NotEmpty(message = "身份证开始时间不能为空")
    private String cardStartDate;

    @ApiModelProperty(value = "身份证失效时间",required = true)
    @NotNull(message = "身份证失效时间不能为空")
    @NotEmpty(message = "身份证失效时间不能为空")
    private String cardEndDate;

    @ApiModelProperty(value = "侧面照2",required = true)
    @NotNull(message = "侧面照不能为空")
    @NotEmpty(message = "侧面照不能为空")
    private String badSideWimg;

    @ApiModelProperty(value = "侧面照",required = true)
    @NotNull(message = "侧面照不能为空")
    @NotEmpty(message = "侧面照不能为空")
    private String badSideOimg;

    @ApiModelProperty(value = "损坏反面照",required = true)
    @NotNull(message = "损坏反面照不能为空")
    @NotEmpty(message = "损坏反面照不能为空")
    private String badReverseImg;

    @ApiModelProperty(value = "损坏正面照",required = true)
    @NotNull(message = "损坏正面照不能为空")
    @NotEmpty(message = "损坏正面照不能为空")
    private String badFrontImg;

    @ApiModelProperty(value = "身份证反面照",required = true)
    @NotNull(message = "身份证反面照不能为空")
    @NotEmpty(message = "身份证反面照不能为空")
    private String cardReverseImg;

    @ApiModelProperty(value = "身份证正面照",required = true)
    @NotNull(message = "身份证正面照不能为空")
    @NotEmpty(message = "身份证正面照不能为空")
    private String cardFrontImg;

    @ApiModelProperty(value = "身份证手持照",required = true)
    @NotNull(message = "身份证手持照不能为空")
    @NotEmpty(message = "身份证手持照不能为空")
    private String cardHandImg;

    @ApiModelProperty(value = "是否为最新1是0否")
    private String isMain;

    @ApiModelProperty(value = "购买凭证图片地址",required = true)
    @NotNull(message = "购买凭证不能为空")
    @NotEmpty(message = "购买凭证不能为空")
    private String voucherImg;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "产品名称",required = true)
    private String productName;

    @ApiModelProperty(value = "投保设备名称",required = true)
    private String insuranceFacilityName;

    @ApiModelProperty(value = "用户id",required = true)
    @NotEmpty(message = "用户id不能为空")
    private String userId;

    @ApiModelProperty(value = "不合格原因")
    private String disqualification;

    @ApiModelProperty(value = "案件号")
    private String clmNo;
}