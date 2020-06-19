package com.dxt.ht.svc.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组合产品子表
 *
 * @author wangfanlgin
 * @date 2020/04/20
 */
@Data
@ApiModel
public class HtGroupProductChild {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "是否购买立即生效1是0否")
    private String isImmediately;

    @ApiModelProperty(value = "购买多少天后生效")
    private Integer takeDay;

    @ApiModelProperty(value = "有效期")
    private Integer validity;

    @ApiModelProperty(value = "终止规则：换机次数")
    private String changeFrequency;

    @ApiModelProperty(value = "终止规则：剩余保费")
    private String remainCoverage;

    @ApiModelProperty(value = "终止规则：服务有效期")
    private String serveValidity;

    @ApiModelProperty(value = "终止规则：维修次数")
    private String maintenanceFrequency;

    @ApiModelProperty(value = "终止规则：维修金限制")
    private String maintenanceAmount;

    @ApiModelProperty(value = "终止规则：恢复次数")
    private String recoverFrequency;

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

    @ApiModelProperty(value = "生效时间")
    private String effectTime;

    @ApiModelProperty(value = "复合规则简述")
    private String terminationRulesSketch;

    @ApiModelProperty(value = "产品类型")
    private String productType;

}