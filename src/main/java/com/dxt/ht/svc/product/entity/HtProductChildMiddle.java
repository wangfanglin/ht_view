package com.dxt.ht.svc.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组合产品中间表
 *
 * @author wangfanglin
 * @date 2020/04/15
 */
@ApiModel
@Data
public class HtProductChildMiddle {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "产品组合表id")
    private String groupProductId;

    @ApiModelProperty(value = "产品组合子表id")
    private String groupProductChildId;

}