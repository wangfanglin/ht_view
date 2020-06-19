package com.dxt.ht.svc.apply.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 渤海报案
 *
 * @author wangfanglin
 * @since 2020/04/22
 */

public class ReportCaseParameter implements Parameter {

    /**
     * 报案图片流水号
     */
    private String replmgSerNo;
    /**
     * IMEI/SN
     */
    private String deviceCode;
    /**
     * 报案人姓名
     */
    private String linkName;
    /**
     * 报案时间
     */
    private String rptDate;
    /**
     * 省代码
     */
    private String province;
    /**
     * 市代码
     */
    private String city;
    /**
     * 区代码
     */
    private String district;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 报案人身份证号
     */
    private String idCard;
    /**
     * 设备分类ID
     */
    private String deviceType;
    /**
     * 设备品牌ID
     */
    private String deviceBrand;
    /**
     * 设备型号ID
     */
    private String deviceModel;
    /**
     * 颜色ID
     */
    private String deviceAttr;
    /**
     * 出险小类
     */
    private String accdntSubcaus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 出险大类
     */
    private String accdntCaus;
    /**
     * 垫付标识
     */
    private String isJKXAdvancedFlag;
    /**
     * 维修方式
     */
    private String repair;
    /**
     * 报案方式
     */
    private String rptWay;
    /**
     * 出险时间
     */
    private String visit;
    /**
     * 出险经过
     */
    private String accdntDtl;
    /**
     * 理赔类型
     */
    private String kindCode;
    /**
     * 报案操作员（处理员）
     */
    private String rptCde;
    /**
     * 报案渠道
     */
    private String channelCode;
    /**
     * 故障信息
     */
    private List<AssessList> assessList;
    /**
     * 故障金额之和
     */
    private String sugAllAmt;
    /**
     * 维修总金额
     */
    private String allAmt;
    /**
     * 和德HD劲螭JC
     */
    private String systemId;


    public ReportCaseParameter(String province, String deviceCode, String linkName, String rptDate, String city, String district, String tel, String idCard, String deviceType, String deviceModel, String deviceAttr, String accdntSubcaus, List<AssessList> assessList, String sugAllAmt, String systemId) {
        this.province = province;
        this.deviceCode = deviceCode;
        this.linkName = linkName;
        this.rptDate = rptDate;
        this.city = city;
        this.district = district;
        this.tel = tel;
        this.idCard = idCard;
        this.deviceType = deviceType;
        this.deviceModel = deviceModel;
        this.accdntSubcaus = accdntSubcaus;
        this.deviceAttr = deviceAttr;
        this.assessList = assessList;
        this.sugAllAmt = sugAllAmt;
        this.systemId = systemId;
    }


    public String getReplmgSerNo() {
        return replmgSerNo;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public String getLinkName() {
        return linkName;
    }

    public String getRptDate() {
        return rptDate;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getDeviceAttr() {
        return deviceAttr;
    }

    public String getAccdntSubcaus() {
        return accdntSubcaus;
    }

    public String getRemark() {
        return remark;
    }

    public String getAccdntCaus() {
        return accdntCaus;
    }

    public String getIsJKXAdvancedFlag() {
        return isJKXAdvancedFlag;
    }

    public String getRepair() {
        return repair;
    }

    public String getRptWay() {
        return rptWay;
    }

    public String getVisit() {
        return visit;
    }

    public String getAccdntDtl() {
        return accdntDtl;
    }

    public String getKindCode() {
        return kindCode;
    }

    public String getRptCde() {
        return rptCde;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public List<AssessList> getAssessList() {
        return assessList;
    }

    public String getSugAllAmt() {
        return sugAllAmt;
    }

    public String getAllAmt() {
        return allAmt;
    }

    public String getSystemId() {
        return systemId;
    }


    public void setReplmgSerNo(String replmgSerNo) {
        this.replmgSerNo = replmgSerNo;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public void setRptDate(String rptDate) {
        this.rptDate = rptDate;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public void setDeviceAttr(String deviceAttr) {
        this.deviceAttr = deviceAttr;
    }

    public void setAccdntSubcaus(String accdntSubcaus) {
        this.accdntSubcaus = accdntSubcaus;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setAccdntCaus(String accdntCaus) {
        this.accdntCaus = accdntCaus;
    }

    public void setIsJKXAdvancedFlag(String isJKXAdvancedFlag) {
        this.isJKXAdvancedFlag = isJKXAdvancedFlag;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public void setRptWay(String rptWay) {
        this.rptWay = rptWay;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public void setAccdntDtl(String accdntDtl) {
        this.accdntDtl = accdntDtl;
    }

    public void setKindCode(String kindCode) {
        this.kindCode = kindCode;
    }

    public void setRptCde(String rptCde) {
        this.rptCde = rptCde;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public void setAssessList(List<AssessList> assessList) {
        this.assessList = assessList;
    }

    public void setSugAllAmt(String sugAllAmt) {
        this.sugAllAmt = sugAllAmt;
    }

    public void setAllAmt(String allAmt) {
        this.allAmt = allAmt;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    @Override
    public boolean checkMandatory() {

        if(assessList!=null && !Parameter.checkListCheckMandatory(assessList)) {
            return false;
        }

        return StringUtils.isNotEmpty(deviceCode)&& StringUtils.isNotEmpty(linkName)
                && StringUtils.isNotEmpty(rptDate)&& StringUtils.isNotEmpty(city)
                && StringUtils.isNotEmpty(district)&& StringUtils.isNotEmpty(tel)
                && StringUtils.isNotEmpty(idCard)&& StringUtils.isNotEmpty(deviceType)
                && StringUtils.isNotEmpty(deviceModel)&& StringUtils.isNotEmpty(accdntSubcaus)
                && StringUtils.isNotEmpty(deviceAttr)&& StringUtils.isNotEmpty(sugAllAmt)
                && StringUtils.isNotEmpty(systemId);
    }
}
