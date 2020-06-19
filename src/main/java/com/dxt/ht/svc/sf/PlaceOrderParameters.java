package com.dxt.ht.svc.sf;

public class PlaceOrderParameters {

    private String orderId;
    private String expressType;
    private String jContact;
    private String jMobile;
    private String jAddress;
    private String dCompany ;
    private String dContact;
    private String dTel;
    private String dAddress;
    private String parcelQuantity;
    private String payMethod;
    private String custId;
    private String sendStartTime;
    /**
     *
     * @param orderId               客户顺丰单号,建议英文字母+YYMMDD(日期)+流水号,如:TB1207300000001
     * @param jContact              寄件方联系人,如果需要生成电子面单,则为必填。
     * @param jMobile               寄件方手机
     * @param jAddress              寄件方详细地址,包括省市区,示例:“广东省深圳市福田区新洲十一街万基商务大厦10楼” ,如果需要生成电子面单,则必填。
     * @param dCompany              到件方公司名称
     * @param dContact              到件方联系人
     * @param dTel                  到件方联系电话
     * @param dAddress              到件方详细地址,如果不传输d_province/d_city字段,此详细地址需包含省市信息,以提高地址识别的成功率,示例:“广东省深圳市福田区新洲十一街万基商务大厦10楼”。
     * @param parcelQuantity        包裹数,一个包裹对应一个运单号,如果是大于1个包裹,则返回则按照子母件的方式返回母运单号和子运单号。
     */
    public PlaceOrderParameters(String orderId, String jContact, String jMobile, String jAddress, String dCompany, String dContact, String dTel, String dAddress, String parcelQuantity,String sendStartTime) {
        this.orderId = orderId;
        this.expressType = "";
        this.jContact = jContact;
        this.jMobile = jMobile;
        this.jAddress = jAddress;
        this.dCompany = dCompany;
        this.dContact = dContact;
        this.dTel = dTel;
        this.dAddress = dAddress;
        this.parcelQuantity = parcelQuantity;
        this.payMethod = "3";
        this.custId = "7551234567";
        this.sendStartTime = sendStartTime;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
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

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public String getParcelQuantity() {
        return parcelQuantity;
    }

    public void setParcelQuantity(String parcelQuantity) {
        this.parcelQuantity = parcelQuantity;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }


    public String getSendStartTime() {
        return sendStartTime;
    }

    public void setSendStartTime(String sendStartTime) {
        this.sendStartTime = sendStartTime;
    }
}
