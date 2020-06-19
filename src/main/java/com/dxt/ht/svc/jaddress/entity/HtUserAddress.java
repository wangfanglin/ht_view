package com.dxt.ht.svc.jaddress.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel
public class HtUserAddress {
    @ApiModelProperty(value = "地址id")
    private int id;
    @ApiModelProperty(value = "用户id",required=true)
    @NotNull(message = " 用户id不能为空")
    @NotEmpty(message = "用户id不能为空")
    private String userId;
    @ApiModelProperty(value = "用户姓名",required=true)
    @NotNull(message = "用户姓名不能为空")
    @NotEmpty(message = "用户姓名不能为空")
    private String userName;
    @ApiModelProperty(value = "用户手机号",required=true)
    @NotNull(message = "用户手机号不能为空")
    @NotEmpty(message = "用户手机号不能为空")
    private String userPhone;
    @ApiModelProperty(value = "省",required=true)
    @NotNull(message = "省不能为空")
    @NotEmpty(message = "省不能为空")
    private String provinceCode;
    @ApiModelProperty(value = "市",required=true)
    @NotNull(message = "市不能为空")
    @NotEmpty(message = "市不能为空")
    private String cityCode;
    @ApiModelProperty(value = "区",required=true)
    @NotNull(message = "区不能为空")
    @NotEmpty(message = "区不能为空")
    private String areaCode;
    @ApiModelProperty(value = "省市区",required=true)
    @NotNull(message = "省市区不能为空")
    @NotEmpty(message = "省市区不能为空")
    private String provinceName;
    @ApiModelProperty(value = "详细地址",required=true)
    @NotNull(message = "详细地址不能为空")
    @NotEmpty(message = "详细地址不能为空")
    private String address;
    @ApiModelProperty(value = "默认地址",required=true)
    @NotNull(message = "默认地址不能为空")
    @NotEmpty(message = "默认地址不能为空")
    private String isDefault;
    @ApiModelProperty(value = "创建时间")
    private Date updateDate;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "删除标识")
    private String  delFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
