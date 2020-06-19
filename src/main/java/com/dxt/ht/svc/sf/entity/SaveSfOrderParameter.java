package com.dxt.ht.svc.sf.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class SaveSfOrderParameter {
    @ApiModelProperty(value = "用户地址id",required=true)
    @NotNull(message = " 用户地址id不能为空")
    @NotEmpty(message = "用户地址id不能为空")
    private String addressId;
    @ApiModelProperty(value = "用户id",required=true)
    @NotNull(message = " 用户id不能为空")
    @NotEmpty(message = "用户id不能为空")
    private  String userId;
    @ApiModelProperty(value = "订单id",required=true)
    @NotNull(message = " 订单id不能为空")
    @NotEmpty(message = "订单id不能为空")
    private  String orderId;
    @ApiModelProperty(value = "取件时间",required=true)
    @NotNull(message = " 取件时间不能为空")
    @NotEmpty(message = "取件时间不能为空")
    private String date;
    @ApiModelProperty(value = "id",required=true)
    @NotNull(message = " id不能为空")
    @NotEmpty(message = "id不能为空")
    private String id;

}
