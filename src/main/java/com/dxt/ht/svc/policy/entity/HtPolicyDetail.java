package com.dxt.ht.svc.policy.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 保单明细
 *
 * @author xxx
 * @date  xxx
 */
@Data
@ApiModel
public class HtPolicyDetail {

    @ApiModelProperty(value = "保单明细id")
    private Integer id;

    @ApiModelProperty(value = "保单id")
    private String policyId;

    @ApiModelProperty(value = "保额")
    private BigDecimal coverage;

    @ApiModelProperty(value = "终止时间")
    private String endTime;

    @ApiModelProperty(value = "有效期")
    private Integer validity;

    @ApiModelProperty(value = "生效时间")
    private String effectTime;

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

}