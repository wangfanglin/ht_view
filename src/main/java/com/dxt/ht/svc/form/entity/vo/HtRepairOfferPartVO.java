package com.dxt.ht.svc.form.entity.vo;

import com.dxt.ht.svc.form.entity.HtRepairOfferPart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 维修工单-待报价-损害部位VO表
 *
 * @author wangfanglin
 * @date 2020/04/01
 */

@Data
@ApiModel
public class HtRepairOfferPartVO extends HtRepairOfferPart {

    @ApiModelProperty(value = "配件名称")
    private String name;

    @ApiModelProperty(value = "是否原厂，1原厂2非原厂")
    private String isOriginal;

}
