package com.dxt.ht.svc.policy.entity.vo;

import com.dxt.ht.svc.form.entity.vo.HtRepairOfferPartVO;
import com.dxt.ht.svc.policy.entity.HtPolicyInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 保单VO
 *
 * @author wangfanglin
 * @date 2020/04/01
 */

@ApiModel
@Data
public class HtPolicyInfoVO extends HtPolicyInfo {

    @ApiModelProperty(value = "产品名称")
    private String proName;

    @ApiModelProperty(value = "投保设备型号")
    private String model;

    @ApiModelProperty(value = "投保设备名称")
    private String name;

    List<HtRepairOfferPartVO> htRepairOfferPartVOs;
}
