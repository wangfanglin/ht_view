package com.dxt.ht.svc.wx;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: jeesite-web
 * @Package: com.jeesite.modules.constant
 * @Description: 配置常量类
 * @Author: Z.G.C
 * @CreateDate: 2019/7/22 4:41 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/7/22 4:41 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */

@Component
@Data
@ToString
public class Constant {


    /**
     * 短信配置信息
     */
    //@Value("${sms.infurl}")
    public String infurl;

   // @Value("${sms.username}")
    private String username;

   // @Value("${sms.password}")
    private String password;

   // @Value("${sms.epid}")
    private String epid;

    /**
     * 支付配置信息
     */
   // @Value("${pay.returnUrlWx}")
    private String returnUrlWx;

   // @Value("${pay.returnUrlAli}")
    private String returnUrlAli;
    
  //  @Value("${pay.redirectUrl}")
    private String redirectUrl;

    /**
     * 微信配置信息
     */
    @Value("${wechat.appId}")
    private String wechatAppId;

    @Value("${wechat.appSecret}")
    private String wechatAppSecret;

}
