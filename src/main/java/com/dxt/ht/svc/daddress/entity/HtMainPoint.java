package com.dxt.ht.svc.daddress.entity;

import com.dxt.ht.svc.form.entity.HtFormInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel
public class HtMainPoint {
    @ApiModelProperty(value = "维修网店id",required=true)
    @NotNull(message = " 维修网店id不能为空")
    @NotEmpty(message = "维修网店id不能为空")
    private String id;
    @ApiModelProperty(value = "维修网点名称")
    private String maintenancePointName;
    @ApiModelProperty(value = "工单类型")
    private  String formType;
    @ApiModelProperty(value = "联系人1")
    private String contactOne;
    @ApiModelProperty(value = "联系电话1")
    private String contactNumberOne;
    @ApiModelProperty(value = "联系人2")
    private String contactTwo;
    @ApiModelProperty(value = "联系电话2")
    private String contactNumberTwo;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "区")
    private String area;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "合同有效开始")
    private Date contractLifeStart;
    @ApiModelProperty(value = "合同有效结束")
    private Date contractLifeEnd;
    @ApiModelProperty(value = "门头照片")
    private String doorPicture;
    @ApiModelProperty(value = "是否接单")
    private String whetherOrder;
    @ApiModelProperty(value = "机构id")
    private String organizationId;
    @ApiModelProperty(value = "状态（0正常 1删除 2停用）",required=true)
    @NotNull(message = " 状态（0正常 1删除 2停用）不能为空")
    @NotEmpty(message = "状态（0正常 1删除 2停用）不能为空")
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
    @ApiModelProperty(value = "经纬度")
    private String longitudeLatitude;
    @ApiModelProperty(value = "覆盖省")
    private String coverageProvince;
    @ApiModelProperty(value = "覆盖市")
    private String coverageCity;
    public String getFormType() { return formType; }
    public void setFormType(String formType) { this.formType = formType; }
    public String getLongitudeLatitude() {
        return longitudeLatitude;
    }

    public void setLongitudeLatitude(String longitudeLatitude) {
        this.longitudeLatitude = longitudeLatitude;
    }

    public String getCoverageProvince() {
        return coverageProvince;
    }

    public void setCoverageProvince(String coverageProvince) {
        this.coverageProvince = coverageProvince;
    }

    public String getCoverageCity() {
        return coverageCity;
    }

    public void setCoverageCity(String coverageCity) {
        this.coverageCity = coverageCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaintenancePointName() {
        return maintenancePointName;
    }

    public void setMaintenancePointName(String maintenancePointName) {
        this.maintenancePointName = maintenancePointName;
    }

    public String getContactOne() {
        return contactOne;
    }

    public void setContactOne(String contactOne) {
        this.contactOne = contactOne;
    }

    public String getContactNumberOne() {
        return contactNumberOne;
    }

    public void setContactNumberOne(String contactNumberOne) {
        this.contactNumberOne = contactNumberOne;
    }

    public String getContactTwo() {
        return contactTwo;
    }

    public void setContactTwo(String contactTwo) {
        this.contactTwo = contactTwo;
    }

    public String getContactNumberTwo() {
        return contactNumberTwo;
    }

    public void setContactNumberTwo(String contactNumberTwo) {
        this.contactNumberTwo = contactNumberTwo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getContractLifeStart() {
        return contractLifeStart;
    }

    public void setContractLifeStart(Date contractLifeStart) {
        this.contractLifeStart = contractLifeStart;
    }

    public Date getContractLifeEnd() {
        return contractLifeEnd;
    }

    public void setContractLifeEnd(Date contractLifeEnd) {
        this.contractLifeEnd = contractLifeEnd;
    }

    public String getDoorPicture() {
        return doorPicture;
    }

    public void setDoorPicture(String doorPicture) {
        this.doorPicture = doorPicture;
    }

    public String getWhetherOrder() {
        return whetherOrder;
    }

    public void setWhetherOrder(String whetherOrder) {
        this.whetherOrder = whetherOrder;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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

    private HtFormInfo htFormInfo;

    public HtFormInfo getHtFormInfo() {
        return htFormInfo;
    }

    public void setHtFormInfo(HtFormInfo htFormInfo) {
        this.htFormInfo = htFormInfo;
    }
}