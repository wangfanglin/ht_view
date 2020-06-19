package com.dxt.ht.svc.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 组合产品对象
 *
 * @author wangfanglin
 * @date 2020/04/15
 */
@Data
@ApiModel
public class HtGroupProductInfo {

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "组合方式")
    private String combinationType;

    @ApiModelProperty(value = "是否单独设置1是0否")
    private String isSingleValidity;

    @ApiModelProperty(value = "是否自立启用1是0否")
    private String isStart;

    @ApiModelProperty(value = "终止规则 复合规则 0单一规则")
    private String terminationRules;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改人")
    private Date updateDate;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "原因")
    private String remark;

    @ApiModelProperty(value = "状态：0正常 1删除 2停用")
    private String status;

    @ApiModelProperty(value = "有效期是否一直1是0否")
    private String isAccordance;

    @ApiModelProperty(value = "销售金额上限")
    private BigDecimal maxPrice;

    @ApiModelProperty(value = "销售金额下限")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "产品保额")
    private BigDecimal coverage;

    @ApiModelProperty(value = "标的金额是否未保额1是0否")
    private String priceFlag;

    @ApiModelProperty(value = "终止规则检测项")
    private String terminationRulesItem;

    @ApiModelProperty(value = "简述终止规则")
    private String terminationRulesSketch;

}