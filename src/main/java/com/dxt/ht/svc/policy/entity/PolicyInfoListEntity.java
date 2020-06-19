package com.dxt.ht.svc.policy.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel
public class PolicyInfoListEntity {

    @ApiModelProperty(value = "userId")
    private String userId;
    @ApiModelProperty(value = "IMEI,Phone,idCard")
    private String queryParam;
    @ApiModelProperty(value = "1：IMEI 2：Phone 3：idCard")
    private String quertType;

}
