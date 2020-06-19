package com.dxt.ht.svc.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 机型库
 *
 * @author xxx
 * @date xxx
 */
@Data
@ApiModel
public class HtPhoneModelInfo {

    @ApiModelProperty(value = "机型库id")
    private String id;

    @ApiModelProperty(value = "设备品牌id")
    private String brandId;

    @ApiModelProperty(value = "渠道商id")
    private String distributionId;

    @ApiModelProperty(value = "型号id")
    private String model;

    @ApiModelProperty(value = "区间最低价")
    private BigDecimal priceLow;

    @ApiModelProperty(value = "区间最高价")
    private BigDecimal priceHigh;

    @ApiModelProperty(value = "手机价格")
    private BigDecimal phonePrice;

    @ApiModelProperty(value = "屏幕价格")
    private BigDecimal screenPrice;

    @ApiModelProperty(value = "xxx")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private String updateDate;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "原因")
    private String remark;

    @ApiModelProperty(value = "状态：0正常1删除2停用")
    private String status;

}