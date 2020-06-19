package com.dxt.ht.svc.form.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 字典表
 *
 * @author xxx
 * @date xxx
 */
@Data
@ApiModel
public class JsSysDictData {

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "父级编号",required = true)
    @NotNull(message = " 父级编号不能为空")
    @NotEmpty(message = "父级编号不能为空")
    private String parentCode;

    @ApiModelProperty(value = "所有父级编号")
    private String parentCodes;

    @ApiModelProperty(value = "本级排序号（升序）")
    private Long treeSort;

    @ApiModelProperty(value = "所有级别排序号")
    private String treeSorts;

    @ApiModelProperty(value = "是否最末级")
    private String treeLeaf;

    @ApiModelProperty(value = "层次级别")
    private Short treeLevel;

    @ApiModelProperty(value = "全节点名")
    private String treeNames;

    @ApiModelProperty(value = "字典标签")
    private String dictLabel;

    @ApiModelProperty(value = "字典键值")
    private String dictValue;

    @ApiModelProperty(value = "字典类型")
    private String dictType;

    @ApiModelProperty(value = "系统内置（1是 0否）")
    private String isSys;

    @ApiModelProperty(value = "字典描述")
    private String description;

    @ApiModelProperty(value = "css样式（如：color:red)")
    private String cssStyle;

    @ApiModelProperty(value = "css类名（如：red）")
    private String cssClass;

    @ApiModelProperty(value = "状态（0正常 1删除 2停用）")
    private String status;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private String updateDate;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "租户代码")
    private String corpCode;

    @ApiModelProperty(value = "租户名称")
    private String corpName;

    @ApiModelProperty(value = "扩展 String 1")
    private String extendS1;

    @ApiModelProperty(value = "扩展 String 2")
    private String extendS2;

    @ApiModelProperty(value = "扩展 String 3")
    private String extendS3;

    @ApiModelProperty(value = "扩展 String 4")
    private String extendS4;

    @ApiModelProperty(value = "扩展 String 5")
    private String extendS5;

    @ApiModelProperty(value = "扩展 String 6")
    private String extendS6;

    @ApiModelProperty(value = "扩展 String 7")
    private String extendS7;

    @ApiModelProperty(value = "扩展 String 8")
    private String extendS8;

    @ApiModelProperty(value = "扩展 Integer 1")
    private BigDecimal extendI1;

    @ApiModelProperty(value = "扩展 Integer 2")
    private BigDecimal extendI2;

    @ApiModelProperty(value = "扩展 Integer 3")
    private BigDecimal extendI3;

    @ApiModelProperty(value = "扩展 Integer 4")
    private BigDecimal extendI4;

    @ApiModelProperty(value = "扩展 Float 1")
    private BigDecimal extendF1;

    @ApiModelProperty(value = "扩展 Float 2")
    private BigDecimal extendF2;

    @ApiModelProperty(value = "扩展 Float 3")
    private BigDecimal extendF3;

    @ApiModelProperty(value = "扩展 Float 4")
    private BigDecimal extendF4;

    @ApiModelProperty(value = "扩展 Date 1")
    private Date extendD1;

    @ApiModelProperty(value = "扩展 Date 2")
    private Date extendD2;

    @ApiModelProperty(value = "扩展 Date 3")
    private Date extendD3;

    @ApiModelProperty(value = "扩展 Date 4")
    private Date extendD4;

}