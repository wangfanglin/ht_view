package com.dxt.ht.svc.wx.pay;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@Data
@ToString
public class WxConfig implements WXPayConfig {

    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.mchId}")
    private String mchId;
    @Value("${wechat.payKey}")
    private String key;


    @Override
    public String getAppID() {
        return this.appId;
    }

    @Override
    public String getMchID() {
        return this.mchId;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
