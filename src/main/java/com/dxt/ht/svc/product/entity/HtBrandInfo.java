package com.dxt.ht.svc.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 设备品牌
 *
 * @author xxx
 * @date xxx
 *
 */

@Data
@ApiModel
public class HtBrandInfo {

    @ApiModelProperty(value = "设备品牌id")
    private String id;

    @ApiModelProperty(value = "设备品牌名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private String updateDate;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "原因")
    private String remark;

    @ApiModelProperty(value = "状态：0正常 1删除 2停用")
    private String status;

}