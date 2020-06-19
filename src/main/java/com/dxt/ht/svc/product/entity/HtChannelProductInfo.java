package com.dxt.ht.svc.product.entity;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 渠道产品表
 *
 * @author wangfanglin
 * @date 2020/04/15
 */
@Data
@ApiModel
public class HtChannelProductInfo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "渠道商名称")
    private String distributionName;

    @ApiModelProperty(value = "渠道商id")
    private String distributionId;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "数据产数方式1 API 2 EXCEL 3 APP")
    private String dataTransferType;

    @ApiModelProperty(value = "服务成本价")
    private BigDecimal serveCostPrice;

    @ApiModelProperty(value = "费率")
    private BigDecimal rate;

    @ApiModelProperty(value = "保费")
    private BigDecimal premium;

    @ApiModelProperty(value = "建议零售价")
    private BigDecimal suggestedRetailPrice;

    @ApiModelProperty(value = "中介供应商")
    private String intermediaryServiceId;

    @ApiModelProperty(value = "是否立即启用1是0否")
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
    private String reamrk;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "组合产品id")
    private String groupProductId;
}