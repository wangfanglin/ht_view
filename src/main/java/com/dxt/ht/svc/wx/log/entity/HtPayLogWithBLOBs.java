package com.dxt.ht.svc.wx.log.entity;

public class HtPayLogWithBLOBs extends HtPayLog {
    private String param;

    private String result;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}