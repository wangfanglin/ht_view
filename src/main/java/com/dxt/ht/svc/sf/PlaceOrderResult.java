package com.dxt.ht.svc.sf;

import java.util.Map;

public class PlaceOrderResult{
    private SfService.PlaceOrderResultStatus status;
    private String orderId;
    private String mailNo;


    public PlaceOrderResult(SfService.PlaceOrderResultStatus status) {
        this.status = status;
    }

    public PlaceOrderResult(Map<String,Object> result) {
        this.status = SfService.checkPlaceOrder(result);
        if(status.isSuccess()){
            Map<String,Object> response = (Map<String, Object>) result.get("Response");
            Map<String,Object> body = (Map<String, Object>) response.get("Body");
            Map<String,Object> orderResponse = (Map<String, Object>) body.get("OrderResponse");
            this.orderId = (String) orderResponse.get("@orderid");
            this.mailNo = (String) orderResponse.get("@mailno");
        }
    }

    public SfService.PlaceOrderResultStatus getStatus() {
        return status;
    }

    public void setStatus(SfService.PlaceOrderResultStatus status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }
}
