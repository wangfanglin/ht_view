package com.dxt.ht.svc.filters.util;

public enum SignStatus {
    /**
     * 验签成功
     */
    succeed("验签成功"),
    /**
     *超时
     */
    time_out("超时"),
    /**
     *参数异常
     */
    params_error("参数异常"),
    /**
     *验签失败
     */
    sign_fail("验签失败");

    private String content;

    SignStatus(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
