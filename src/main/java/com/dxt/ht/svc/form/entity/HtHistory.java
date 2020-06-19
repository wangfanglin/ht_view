package com.dxt.ht.svc.form.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel
public class HtHistory {

    @ApiModelProperty(value = "操作历史id")
    private String id;

    @ApiModelProperty(value = "工单id",required = true)
    @NotNull(message = " 工单id不能为空")
    @NotEmpty(message = "工单id不能为空")
    private String workOrderId;

    @ApiModelProperty(value = "表单id")
    @NotNull(message = " 表单id不能为空")
    @NotEmpty(message = "表单id不能为空")
    private String formId;

    @ApiModelProperty(value = "工单类型0在线理赔1换新2维修")
    private String formType;

    @ApiModelProperty(value = "流转后环节ID")
    private String afterActivityId;

    @ApiModelProperty(value = "环节id")
    private String activityId;

    @ApiModelProperty(value = "对用户是否可见 1是0否使用系统字典sys_yes_no")
    private String userVisible;

    @ApiModelProperty(value = "操作类型（处理状态）")
    private String operationStatus;

    @ApiModelProperty(value = "后台管理系统是否可见1是0否使用系统字典sys_yes_no")
    private String cmsVisible;

    @ApiModelProperty(value = "处理人ID")
    private String disposeUserId;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private String updateDate;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "表单的历史ID")
    private String historyFormId;

    @ApiModelProperty(value = "分支操作的唯一标识（改派、重启、关闭）")
    private String uniquenessId;

    @ApiModelProperty(value = "环节的名称")
    private String activityName;

    @ApiModelProperty(value = "流转后环节名称")
    private String afterActivityName;

    @ApiModelProperty(value = "是否退回1是")
    private String isBack;

    private JsSysDictData jsSysDictData;

    private String exteriorId;
    }