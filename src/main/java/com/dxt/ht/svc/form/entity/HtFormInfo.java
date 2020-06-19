package com.dxt.ht.svc.form.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel
public class HtFormInfo {

    @ApiModelProperty(value = "工单id")
    private String id;

    @ApiModelProperty(value = "保单id",required = true)
    @NotNull(message = " 用户id不能为空")
    @NotEmpty(message = "用户id不能为空")
    private String policyId;

    @ApiModelProperty(value = "处理状态",required = true)
    private String manageStatus;

    @ApiModelProperty(value = "工单状态")
    private String formStatus;

    @ApiModelProperty(value = "工单类型，1换新，2维修，3投诉")
    private String formType;

    @ApiModelProperty(value = "状态（0正常 1删除 2停用）",required = true)
    @NotNull(message = " 用户id不能为空")
    @NotEmpty(message = "用户id不能为空")
    private String status;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "是否自动分配1是2否")
    private String isAutomatic;

    @ApiModelProperty(value = "维修网点")
    private String officeId;

    @ApiModelProperty(value = "工单是否关闭0正常1关闭默认0")
    private String isRun;

}