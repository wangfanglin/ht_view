package com.dxt.ht.svc.form.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 核赔不合格日志表
 *
 * @author wangfanglin
 * @date 2020/04/07
 */
@Data
@ApiModel
public class HtClaimDisqualificationLog {

    @ApiModelProperty(value = "核赔不合格日志id")
    private String id;

    @ApiModelProperty(value = "工单id")
    private String formId;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "原因，备注")
    private String remark;

    @ApiModelProperty(value = "状态 ： 0正常，1删除，2停用")
    private String status;

}