package com.dxt.ht.svc.area.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@ApiModel
public class JsSysArea {
    @ApiModelProperty(value = "区域编码",required=true)
    @NotNull(message = " 区域编码不能为空")
    @NotEmpty(message = "区域编码不能为空")
    private String areaCode;
    @ApiModelProperty(value = "父级编号",required=true)
    @NotNull(message = " 父级编号不能为空")
    @NotEmpty(message = "父级编号不能为空")
    private String parentCode;
    @ApiModelProperty(value = "所有父级编号",required=true)
    @NotNull(message = " 所有父级编号不能为空")
    @NotEmpty(message = "所有父级编号不能为空")
    private String parentCodes;
    @ApiModelProperty(value = "本级排序号",required=true)
    @NotNull(message = " 本级排序号不能为空")
    @NotEmpty(message = "本级排序号不能为空")
    private Long treeSort;
    @ApiModelProperty(value = "所有级别排序号",required=true)
    @NotNull(message = " 所有级别排序号不能为空")
    @NotEmpty(message = "所有级别排序号不能为空")
    private String treeSorts;
    @ApiModelProperty(value = "是否最末级",required=true)
    @NotNull(message = " 是否最末级不能为空")
    @NotEmpty(message = "是否最末级不能为空")
    private String treeLeaf;
    @ApiModelProperty(value = "层次级别",required=true)
    @NotNull(message = " 层次级别不能为空")
    @NotEmpty(message = "层次级别不能为空")
    private Short treeLevel;
    @ApiModelProperty(value = "全节点名",required=true)
    @NotNull(message = " 全节点名不能为空")
    @NotEmpty(message = "全节点名不能为空")
    private String treeNames;
    @ApiModelProperty(value = "全节点名",required=true)
    @NotNull(message = " 全节点名不能为空")
    @NotEmpty(message = "全节点名不能为空")
    private String areaName;
    @ApiModelProperty(value = "区域类型")
    private String areaType;
    @ApiModelProperty(value = "状态",required=true)
    @NotNull(message = " 状态不能为空")
    @NotEmpty(message = "状态不能为空")
    private String status;
    @ApiModelProperty(value = "创建者",required=true)
    @NotNull(message = " 创建者不能为空")
    @NotEmpty(message = "创建者不能为空")
    private String createBy;
    @ApiModelProperty(value = "创建时间",required=true)
    @NotNull(message = " 创建时间不能为空")
    @NotEmpty(message = "创建时间不能为空")
    private Date createDate;
    @ApiModelProperty(value = "更新者",required=true)
    @NotNull(message = " 更新者不能为空")
    @NotEmpty(message = "更新者不能为空")
    private String updateBy;
    @ApiModelProperty(value = "更新时间",required=true)
    @NotNull(message = " 更新时间不能为空")
    @NotEmpty(message = "更新时间不能为空")
    private Date updateDate;
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCodes() {
        return parentCodes;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
    }

    public Long getTreeSort() {
        return treeSort;
    }

    public void setTreeSort(Long treeSort) {
        this.treeSort = treeSort;
    }

    public String getTreeSorts() {
        return treeSorts;
    }

    public void setTreeSorts(String treeSorts) {
        this.treeSorts = treeSorts;
    }

    public String getTreeLeaf() {
        return treeLeaf;
    }

    public void setTreeLeaf(String treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public Short getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Short treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeNames() {
        return treeNames;
    }

    public void setTreeNames(String treeNames) {
        this.treeNames = treeNames;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}