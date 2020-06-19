package com.dxt.ht.svc.sf;

public class SfXmlUtils {
    private static final String  PlaceOrderXml = "<Request service=\"OrderService\" lang=\"zh-CN\">\n" +
            "<Head>HEAD</Head> \n" +
            "<Body> \n" +
            "\t<Order \n" +
            "\t          orderid=\"ORDER_ID\" \n" +
            "    \t          express_type='EXPRESS_TYPE' \n" +
            "                  j_contact='J_CONTACT'\n" +
            "                  j_mobile='J_MOBILE'\n" +
            "                  j_address='J_ADDRESS'\n" +
            "\t\t\t   d_company='D_COMPANY' \n" +
            "\t\t\t   d_contact='D_CONTACT' \n" +
            "\t\t\t   d_tel='D_TEL' \n" +
            "\t\t\t   d_address='D_ADDRESS'  \n" +
            "                  parcel_quantity='PARCEL_QUANTITY' \n" +
            "                  custid='CUSTID' \n" +
            "                  sendstarttime='SENDSTARTTIME' \n" +
            "                  pay_method='PAY_METHOD'>\n" +
            " </Order>\n" +
            "</Body> \n" +
            "</Request>";



    public static String getPlaceOrderParameter(String head,PlaceOrderParameters parameters){
        String xml = PlaceOrderXml;
        xml = xml.replace("HEAD",head);
        xml = xml.replace("ORDER_ID",parameters.getOrderId());
        xml = xml.replace("EXPRESS_TYPE",parameters.getExpressType());
        xml = xml.replace("J_CONTACT",parameters.getjContact());
        xml = xml.replace("J_MOBILE",parameters.getjMobile());
        xml = xml.replace("J_ADDRESS",parameters.getjAddress());
        xml = xml.replace("D_COMPANY",parameters.getdCompany());
        xml = xml.replace("D_CONTACT",parameters.getdContact());
        xml = xml.replace("D_TEL",parameters.getdTel());
        xml = xml.replace("D_ADDRESS",parameters.getdAddress());
        xml = xml.replace("PARCEL_QUANTITY",parameters.getParcelQuantity());
        xml = xml.replace("PAY_METHOD",parameters.getPayMethod());
        xml = xml.replace("CUSTID",parameters.getCustId());
        xml = xml.replace("SENDSTARTTIME",parameters.getSendStartTime());
        return xml;
    }
}
