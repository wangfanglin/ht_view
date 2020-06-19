package com.dxt.ht.svc.apply.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户地址信息表
 *
 * @author wangfanglin
 * @date 2020/04/09
 */
@Data
@ApiModel
public class HtUserApplyArea {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "省")
    private String provinceCode;

    @ApiModelProperty(value = "市")
    private String cityCode;

    @ApiModelProperty(value = "区")
    private String areaCode;

    @ApiModelProperty(value = "省市区")
    private String provinceName;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

}