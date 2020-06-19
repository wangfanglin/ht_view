package com.dxt.ht.svc.form.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel
@Data
public class HtReceiptData {

    @ApiModelProperty(value = "收款人信息id")
    private String id;

    @ApiModelProperty(value = "工单id",required=true)
    @NotNull(message = " 工单id不能为空")
    @NotEmpty(message = "工单id不能为空")
    private String formId;

    @ApiModelProperty(value = "bdId")
    private String bdId;

    @ApiModelProperty(value = "银行名称",required=true)
    @NotNull(message = " 银行名称不能为空")
    @NotEmpty(message = "银行名称不能为空")
    private String bankName;

    @ApiModelProperty(value = "银行卡号",required=true)
    @NotNull(message = " 银行卡号不能为空")
    @NotEmpty(message = "银行卡号不能为空")
    private String bankNumber;

    @ApiModelProperty(value = "渤海报案号")
    private String bhReportNo;

    @ApiModelProperty(value = "收款人姓名",required=true)
    @NotNull(message = "收款人姓名不能为空")
    @NotEmpty(message = "收款人姓名不能为空")
    private String payeeName;

    @ApiModelProperty(value = "收款人身份证号")
    private String payeeIdNumber;

    @ApiModelProperty(value = "审核状态 0待审核 1审核通过 2驳回")
    private String auditStatus;

    @ApiModelProperty(value = "回传状态")
    private String passBack;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "驳回原因")
    private String remark;

    @ApiModelProperty(value = "省")
    private String provinceCode;

    @ApiModelProperty(value = "市")
    private String cityCode;

    @ApiModelProperty(value = "区")
    private String districtCode;

    @ApiModelProperty(value = "银行卡类别")
    private String bankType;

    @ApiModelProperty(value = "银行卡照片")
    private String bandImg;

    @ApiModelProperty(value = "留存照片")
    private String keepImg;

    @ApiModelProperty(value = "保单id",required=true)
    @NotNull(message = " 保单id不能为空")
    @NotEmpty(message = "保单id不能为空")
    private String insuranceNumber;

    @ApiModelProperty(value = "当前状态,‘0‘默认值   0未填写信息  1已申请待审核")
    private String status;

    @ApiModelProperty(value = "申请时间")
    private String applyDate;

    @ApiModelProperty(value = "审核时间")
    private String examineDate;
}