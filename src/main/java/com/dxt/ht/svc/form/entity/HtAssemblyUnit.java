package com.dxt.ht.svc.form.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 维修部件表
 *
 * @author  wangfanglin
 * @date 2020/04/01
 */

@Data
@ApiModel
public class HtAssemblyUnit {

    @ApiModelProperty(value = "维修部件id")
    private String id;

    @ApiModelProperty(value = "维修部件名称")
    private String name;

    @ApiModelProperty(value = "1主部件0副部件")
    private String mainFlag;

    @ApiModelProperty(value = "类目")
    private String category;

    @ApiModelProperty(value = "是否启用1启用 2不启用")
    private String isStart;

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

    @ApiModelProperty(value = "状态，0正常 1删除 2停用")
    private String status;

}