package com.dxt.ht.svc.form.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 配件表
 *
 * @author  wangfanglin
 * @date 2020/04/01
 */
@Data
@ApiModel
public class HtAccessoriesInfo {

    @ApiModelProperty(value = "配件id")
    private String id;

    @ApiModelProperty(value = "配件名称")
    private String name;

    @ApiModelProperty(value = "配件型号")
    private String type;

    @ApiModelProperty(value = "是否原厂，1原厂2非原厂")
    private String isOriginal;

    @ApiModelProperty(value = "类目")
    private String category;

    @ApiModelProperty(value = "所属部件")
    private String assemblyId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

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

    @ApiModelProperty(value = "状态，0正常1删除2停用")
    private String status;

}