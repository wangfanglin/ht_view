package com.dxt.ht.svc.form.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 维修工单-待报价-损害部位表
 *
 * @author  wangfanglin
 * @date 2020/04/01
 */
@Data
@ApiModel
public class HtRepairOfferPart {

    @ApiModelProperty(value = "维修工单-待报价-损害部位id")
    private String id;

    @ApiModelProperty(value = "工单id")
    private String formId;

    @ApiModelProperty(value = "损害部位id")
    private String damageId;

    @ApiModelProperty(value = "损害部位名称")
    private String damageName;

    @ApiModelProperty(value = "渤海方案id")
    private String bhProjectId;

    @ApiModelProperty(value = "渤海方案名称")
    private String bhProjectName;

    @ApiModelProperty(value = "和德方案id")
    private String hdProjectId;

    @ApiModelProperty(value = "和德方案名称")
    private String hdProjectName;

    @ApiModelProperty(value = "配件报价")
    private BigDecimal damagePrice;

    @ApiModelProperty(value = "是否有残值 0 否  1是")
    private String isSalvage;

    @ApiModelProperty(value = "残值类型")
    private String salvageType;

    @ApiModelProperty(value = "残值价格")
    private Long salvagePrice;

    @ApiModelProperty(value = "状态（0正常 1删除 2停用）")
    private String status;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    private String updateDate;

    @ApiModelProperty(value = "原因")
    private String remarks;

}