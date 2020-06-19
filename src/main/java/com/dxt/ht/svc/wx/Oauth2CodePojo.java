package com.dxt.ht.svc.wx;

/**
 * @ProjectName: airline
 * @Package: com.ticket.airline.modules.wechat
 * @Description: java类作用描述
 * @Author: Z.G.C
 * @CreateDate: 2019/11/19 2:45 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/11/19 2:45 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Oauth2CodePojo {
    private String appId;
    private String appSecret;
    private String code;
    private String grant_type;

    public Oauth2CodePojo(String appId, String appSecret, String code) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.code = code;
    }

    public Oauth2CodePojo() {
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrant_type() {
        return this.grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
