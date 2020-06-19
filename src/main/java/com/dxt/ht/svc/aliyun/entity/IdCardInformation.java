package com.dxt.ht.svc.aliyun.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 身份证信息表
 *
 * @author wangfanglin
 * @date 2020/04/13
 */
@Data
@ApiModel
public class IdCardInformation {

    @ApiModelProperty(value = "用户地址信息")
    private String address;

    @ApiModelProperty(value = "名族")
    private String nationality;

    @ApiModelProperty(value = "身份证号")
    private String num;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "生日")
    private String birth;

    @ApiModelProperty(value = "身份证到期日期")
    private String end_date;

    @ApiModelProperty(value = "身份证开始日期")
    private String start_date;

    @ApiModelProperty(value = "办证公安局地点")
    private String issue;
}
