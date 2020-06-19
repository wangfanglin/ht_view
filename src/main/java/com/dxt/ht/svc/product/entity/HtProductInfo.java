package com.dxt.ht.svc.product.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 产品表（权益）
 *
 * @author xxx
 * @date  xxx
 */

@Data
@ApiModel
public class HtProductInfo {

    @ApiModelProperty(value = "产品id")
    private String id;

    @ApiModelProperty(value = "产品名称")
    private String proName;

    @ApiModelProperty(value = "产品代码")
    private String proCode;

    @ApiModelProperty(value = "xxx")
    private String category;

    @ApiModelProperty(value = "系统类型")
    private String systemType;

    @ApiModelProperty(value = "产品累别")
    private String productType;

    @ApiModelProperty(value = "保额是否为主商品金额1是0否")
    private String priceFlag;

    @ApiModelProperty(value = "权益简介")
    private String maintainStandard;

    @ApiModelProperty(value = "权益地址")
    private String equityUrl;

    @ApiModelProperty(value = "上市时间")
    private String intoMarketDate;

    @ApiModelProperty(value = "退市时间")
    private String exitMarketDate;

    @ApiModelProperty(value = "销售价格下限")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "销售价格上限")
    private BigDecimal maxPrice;

    @ApiModelProperty(value = "机型")
    private String phoneModelId;

    @ApiModelProperty(value = "维修次数")
    private Integer maintenanceFrequency;

    @ApiModelProperty(value = "维修限额是否有限制1是0否")
    private String isRestrict;

    @ApiModelProperty(value = "限制金额")
    private BigDecimal restrictPrice;

    @ApiModelProperty(value = "保险公司维修限额")
    private BigDecimal insuranceRestrictPrice;

    @ApiModelProperty(value = "换机次数")
    private Integer changeFrequency;

    @ApiModelProperty(value = "换机条件")
    private String changeCondition;

    @ApiModelProperty(value = "换机自付额")
    private BigDecimal changePayment;

    @ApiModelProperty(value = "基础折旧率")
    private Integer basisDepreciation;

    @ApiModelProperty(value = "每月折旧率")
    private Integer monthlyDepreciation;

    @ApiModelProperty(value = "服务成本")
    private BigDecimal serveCostPrice;

    @ApiModelProperty(value = "费率")
    private Integer rate;

    @ApiModelProperty(value = "保费")
    private BigDecimal premium;

    @ApiModelProperty(value = "建议零售价")
    private BigDecimal suggestedRetailPrice;

    @ApiModelProperty(value = "保险供应商")
    private String insuranceProviderId;

    @ApiModelProperty(value = "中介服务商")
    private String intermediaryServiceId;

    @ApiModelProperty(value = "1.0字段")
    private String excessRate;

    @ApiModelProperty(value = "1.0字段")
    private String fixExcessRate;

    @ApiModelProperty(value = "1.0字段")
    private String insureLimit;

    @ApiModelProperty(value = "1.0字段")
    private String guaLimt;

    @ApiModelProperty(value = "是否立即启用1是0否")
    private String isStart;

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

    @ApiModelProperty(value = "状态：0正常 1删除 2停用")
    private String status;

    @ApiModelProperty(value = "商品金额")
    private BigDecimal coverage;

    @ApiModelProperty(value = "xxx")
    private String equityIntro;

}