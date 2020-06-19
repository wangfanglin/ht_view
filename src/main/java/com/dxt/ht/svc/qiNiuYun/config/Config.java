package com.dxt.ht.svc.qiNiuYun.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Data
@ToString
public class Config {

    /**
     * 七牛云配置信息
     */
    @Value("${qiNiuYun.accessKey}")
    private String accessKey;

    @Value("${qiNiuYun.secretKey}")
    private String secretKey;

    @Value("${qiNiuYun.bucketName}")
    private String bucketName;
}
