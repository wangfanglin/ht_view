package com.dxt.ht.svc.sf.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@ApiModel
public class SfOrderInfo {
    @ApiModelProperty(value = "顺丰表id",required=true)
    @NotNull(message = " 顺丰表id不能为空")
    @NotEmpty(message = "顺丰表id不能为空")
    private String id;
    @ApiModelProperty(value = "工单id")
    private String orderId;
    @ApiModelProperty(value = "顺丰单号")
    private String sfOrderId;
    @ApiModelProperty(value = "顺丰运单号")
    private String mailNo;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "寄件方联系人,如果需要生成电子面单,则为必填。")
    private String jContact;
    @ApiModelProperty(value = "寄件方手机")
    private String jMobile;
    @ApiModelProperty(value = "寄件方省")
    private String jProvince;
    @ApiModelProperty(value = "寄件方市")
    private String jCity;
    @ApiModelProperty(value = "寄件方区")
    private String jArea;
    @ApiModelProperty(value = "到件方省")
    private String dProvince;
    @ApiModelProperty(value = "到件方市")
    private String dCity;
    @ApiModelProperty(value = "到件方区")
    private String dArea;
    @ApiModelProperty(value = "到件方联系人")
    private String dContact;
    @ApiModelProperty(value = "到件方联系电话")
    private String dTel;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "删除标示")
    private String delFlag;
    @ApiModelProperty(value = "寄件方详细地址,包括省市区,示例:“广东省深圳市福田区新洲十一街万基商务大厦10楼” ,如果需要生成电子面单,则必填。")
    private String jAddress;
    @ApiModelProperty(value = "到件方公司名称")
    private String dCompany;
    @ApiModelProperty(value = "到件方详细地址,如果不传输d_province/d_city字段,此详细地址需包含省市信息,以提高地址识别的成功率,示例:“广东省深圳市福田区新洲十一街万基商务大厦10楼”。")
    private String dAddress;

    public String getdProvince() {
        return dProvince;
    }

    public void setdProvince(String dProvince) {
        this.dProvince = dProvince;
    }

    public String getdCity() {
        return dCity;
    }

    public void setdCity(String dCity) {
        this.dCity = dCity;
    }

    public String getdArea() {
        return dArea;
    }

    public void setdArea(String dArea) {
        this.dArea = dArea;
    }

    public String getjAddress() {
        return jAddress;
    }

    public void setjAddress(String jAddress) {
        this.jAddress = jAddress;
    }

    public String getdCompany() {
        return dCompany;
    }

    public void setdCompany(String dCompany) {
        this.dCompany = dCompany;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSfOrderId() {
        return sfOrderId;
    }

    public void setSfOrderId(String sfOrderId) {
        this.sfOrderId = sfOrderId;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getjContact() {
        return jContact;
    }

    public void setjContact(String jContact) {
        this.jContact = jContact;
    }

    public String getjMobile() {
        return jMobile;
    }

    public void setjMobile(String jMobile) {
        this.jMobile = jMobile;
    }

    public String getjProvince() {
        return jProvince;
    }

    public void setjProvince(String jProvince) {
        this.jProvince = jProvince;
    }

    public String getjCity() {
        return jCity;
    }

    public void setjCity(String jCity) {
        this.jCity = jCity;
    }

    public String getjArea() {
        return jArea;
    }

    public void setjArea(String jArea) {
        this.jArea = jArea;
    }

    public String getdContact() {
        return dContact;
    }

    public void setdContact(String dContact) {
        this.dContact = dContact;
    }

    public String getdTel() {
        return dTel;
    }

    public void setdTel(String dTel) {
        this.dTel = dTel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }


}