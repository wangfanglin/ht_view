package com.dxt.ht.svc.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dxt.ht.svc.common.utils.HttpReqUtils;
import com.dxt.ht.svc.filters.util.JsonUtils;
import com.dxt.ht.svc.redis.RedisUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @ProjectName: airline
 * @Package: com.ticket.airline.utils
 * @Description: java类作用描述
 * @Author: Z.G.C
 * @CreateDate: 2019/11/19 2:31 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/11/19 2:31 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */

@Component
public class WeixinUtil  {


    @Autowired
    RedisUtil redisUtil;
    @Autowired
    WeixinUtil weixinUtil;


    private static String PREFIX_CDATA = "<![CDATA[";
    private static String SUFFIX_CDATA = "]]>";
    public static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);
    public static final String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static String web_oauth_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    public static String web_oauth_accesstoken_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static String web_oauth_userinfo_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    public static String get_callbackip_url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
    public static final String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static final String WEIXIN_ACCESS_TOKEN = "weixin_access_token";
    public static final String APP_ACCESS_TOKEN = "app_access_token";
    public static final String user_info_list_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
    public static final String set_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public WeixinUtil() {
    }

    public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();

        try {
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init((KeyManager[])null, new TrustManager[]{tm}, new SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            OutputStream outputStream;
            if (null != outputStr) {
                outputStream = httpUrlConn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;

            while((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            return buffer.toString();
        } catch (ConnectException var13) {
            logger.debug("Weixin server connection timed out.");
        } catch (Exception var14) {
            logger.debug("https request error:{}" + var14.getMessage());
        }

        return null;
    }

    public static String getHttpsResponse(String url) throws ParseException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String jsonStr = null;

        try {
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");

            try {
                httpget.abort();
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }

        return jsonStr;
    }

    public static String getRequestUrlWithParams(HttpServletRequest request) {
        String backurl = "https://" + request.getServerName() + request.getRequestURI() + "?" + request.getQueryString();
        return backurl;
    }

    public static String gerRequestUrl(HttpServletRequest request) {
        return "https://" + request.getServerName() + request.getRequestURI();
    }

    public static String getRequestPath(HttpServletRequest request) {
        String requestPath = "/" + request.getRequestURI();
        if (requestPath.indexOf("&") > -1) {
            requestPath = requestPath.substring(0, requestPath.indexOf("&"));
        }

        requestPath = requestPath.substring(request.getContextPath().length() + 1);
        return requestPath;
    }

    public static String getOpenid(HttpServletRequest request) {
        return StringUtils.isBlank(request.getParameter("openid")) ? getSessionOpenid(request) : request.getParameter("openid");
    }

    public static String getSessionOpenid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String openid = (String)session.getAttribute("openid");
        return openid == null ? null : openid;
    }

    public  String getAccessToken() throws Exception {
        System.out.println("weixin_access_token："+redisUtil.get("weixin_access_token"));

        return redisUtil.get("weixin_access_token");
    }

    public  String getAppAccessToken() throws Exception {
        return redisUtil.get("app_access_token");
    }

    public  Map<String, Object> getWeixinUserInfo(String openid) throws Exception {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN".replace("ACCESS_TOKEN", getAccessToken()).replace("OPENID", openid);
        String json = httpRequest(requestUrl, "GET", (String)null);
        Map<String, Object> user = new HashMap();
        JSONObject jsonObject = JSONObject.parseObject(json);
        if (jsonObject != null) {
            logger.warn(jsonObject.toString());
            user.put("openId", jsonObject.getString("openid"));
            user.put("nickname", jsonObject.getString("nickname"));
            user.put("headimgurl", jsonObject.getString("headimgurl"));
            user.put("subscribe", jsonObject.getString("subscribe"));
            user.put("sex", jsonObject.getString("sex"));
            user.put("city", jsonObject.getString("city"));
            user.put("country", jsonObject.getString("country"));
            user.put("province", jsonObject.getString("province"));
            user.put("subscribe_time", jsonObject.getString("subscribe_time"));
            user.put("unionid", jsonObject.getString("unionid"));
            user.put("remark", jsonObject.getString("remark"));
            user.put("groupid", jsonObject.getString("groupid"));
        }

        return user;
    }

    public static Map<String, Object> getWeixinUserInfo(String openid, String accessToken) throws Exception {
        String requestUrl = web_oauth_userinfo_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
        logger.warn("请求地址："+requestUrl);
        String json = httpRequest(requestUrl, "GET", (String)null);
        logger.warn("获取的用户信息json"+json);
        Map<String, Object> user = new HashMap();
        JSONObject jsonObject = JSONObject.parseObject(json);
        user.put("openId", jsonObject.getString("openid"));
        user.put("nickname", jsonObject.getString("nickname"));
        user.put("headimgurl", jsonObject.getString("headimgurl"));
        user.put("subscribe", jsonObject.getString("subscribe"));
        user.put("sex", jsonObject.getString("sex"));
        user.put("city", jsonObject.getString("city"));
        user.put("country", jsonObject.getString("country"));
        user.put("province", jsonObject.getString("province"));
        user.put("subscribe_time", jsonObject.getString("subscribe_time"));
        user.put("unionid", jsonObject.getString("unionid"));
        user.put("remark", jsonObject.getString("remark"));
        user.put("groupid", jsonObject.getString("groupid"));
        logger.warn("--------从微信拿到的用户数据----------");
        logger.warn(user.toString());
        logger.warn("-----------------------------------");
        return user;
    }

    public  List getUserInfoList(String lastOpenId) throws Exception {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID".replace("ACCESS_TOKEN", getAccessToken()).replace("NEXT_OPENID", lastOpenId);
        String json = httpRequest(requestUrl, "GET", (String)null);
        new HashMap();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject data = (JSONObject)jsonObject.get("data");
        List openidList = (List)data.get("openid");
        List resultList = new ArrayList();
        int i = 0;

        for(int count = openidList.size(); i < count; ++i) {
            String openid = (String)openidList.get(i);
            Map<String, Object> userinfo = getWeixinUserInfo(openid);
            resultList.add(userinfo);
        }

        return resultList;
    }

    public  List getUserInfoAllList() {
        List resultList = new ArrayList();
        String openId = "";

        while(true) {
            try {
                List<Map<String, Object>> list = getUserInfoList(openId);
                if (list != null) {
                    resultList.addAll(list);
                    if (list.size() >= 10000) {
                        openId = (String)((Map)list.get(list.size() - 1)).get("openId");
                        continue;
                    }
                }

                return resultList;
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }
    }

    public  String getTicket() throws Exception {
        String wx_ticket = "";
//        try{
//            wx_ticket = redisUtil.get("wx_ticket");
//        }catch (Exception e){
//            wx_ticket =   getWxTicket();
//        }
//        if (null == wx_ticket || StringUtils.isBlank(wx_ticket)) {
//            wx_ticket =   getWxTicket();
//        }
//        System.out.println("wx_ticket:"+wx_ticket);
        wx_ticket =   getWxTicket();
        return wx_ticket;
    }

    public  String getWxTicket() throws Exception {
        String accessToken = HttpReqUtils.getWxToken();
        System.out.println("accessToken:"+accessToken);
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        int statusCode = httpClient.executeMethod(getMethod);
        System.out.println("getMethod:"+getMethod);
        System.out.println("statusCode+"+statusCode);
        if (statusCode != 200) {
            System.err.println("Method failed: " + getMethod.getStatusLine());
        }

        Map<String, Object> reMap = JSON.parseObject(getMethod.getResponseBodyAsString());
        System.out.println("reMap:"+reMap);
        String ticket = (String)reMap.get("ticket");
//        redisUtil.setEx("wx_ticket", ticket, 1, TimeUnit.HOURS);
        System.out.println("wx_ticket："+ticket);
        return ticket;
    }

    public  Map<String, Object> navigationMap(String pathURL,String appId) throws Exception {
        System.out.println("appId："+appId);
        Map<String, Object> map = new HashMap();
        Map<String, String> signMap = MapSign.sign(getTicket(), pathURL);
        map.put("signature", signMap.get("signature"));
        map.put("nonceStr", signMap.get("nonceStr"));
        map.put("timestamp", signMap.get("timestamp"));
        map.put("appid", appId);
        return map;
    }

    public  String createMenu(String params) throws Exception {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", getAccessToken());
        String json = httpRequest(requestUrl, "GET", params);
        logger.debug("menu result: " + json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.getString("errmsg");
    }

    public byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        boolean var4 = false;

        int leng;
        while((leng = inStream.read(buff)) != -1) {
            out.write(buff, 0, leng);
        }

        inStream.close();
        out.close();
        return out.toByteArray();
    }

    public String paserRequestToString(HttpServletRequest request, String charSet) throws IOException {
        byte[] byteArray = this.readInputStream(request.getInputStream());
        return new String(byteArray, charSet);
    }

    public Object xmlToJavaBean(Map<String, String> objMap, String xml) {
        XStream xStream = this.getXStream(false);

        try {
            Set s = objMap.entrySet();
            Iterator it = s.iterator();

            while(it.hasNext()) {
                Map.Entry<String, String> item = (Map.Entry)it.next();
                String rootElementName = (String)item.getKey();
                String className = (String)item.getValue();
                xStream.alias(rootElementName, Class.forName(className));
            }

            return xStream.fromXML(xml);
        } catch (ClassNotFoundException var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public String javaBeanToXml(Map<String, String> objMap, Object obj) {
        XStream xStream = this.getXStream(true);

        try {
            Set s = objMap.entrySet();
            Iterator it = s.iterator();

            while(it.hasNext()) {
                Map.Entry<String, String> item = (Map.Entry)it.next();
                String rootElementName = (String)item.getKey();
                String className = (String)item.getValue();
                xStream.alias(rootElementName, Class.forName(className));
            }

            return xStream.toXML(obj);
        } catch (ClassNotFoundException var9) {
            return null;
        }
    }

    public String javaBeanToXml(String other, String className, Object obj) {
        try {
            XStream xStream = this.getXStream(true);
            xStream.alias(other, Class.forName(className));
            return xStream.toXML(obj);
        } catch (ClassNotFoundException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public XStream getXStream(boolean isAddCDATA) {
        XStream xstream = null;
        if (isAddCDATA) {
            xstream = new XStream(new XppDriver() {
                public HierarchicalStreamWriter createWriter(Writer out) {
                    return new PrettyPrintWriter(out) {
                        protected void writeText(QuickWriter writer, String text) {
                            if (!text.startsWith(WeixinUtil.PREFIX_CDATA)) {
                                text = WeixinUtil.PREFIX_CDATA + text + WeixinUtil.SUFFIX_CDATA;
                            }

                            writer.write(text);
                        }
                    };
                }
            });
        } else {
            xstream = new XStream();
        }

        return xstream;
    }

    public void outMessage(HttpServletResponse response, String xmlMsg) {
        try {
            PrintWriter pw = response.getWriter();
            pw.write(xmlMsg);
            pw.flush();
            pw.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public static Map<String, Object> getOauth2AccessToken(Oauth2CodePojo oauth2CodePojo) {
        String requestUrl = WeixinUtil.web_oauth_accesstoken_url;
        requestUrl = requestUrl.replace("APPID", oauth2CodePojo.getAppId());
        requestUrl = requestUrl.replace("SECRET", oauth2CodePojo.getAppSecret());
        requestUrl = requestUrl.replace("CODE", oauth2CodePojo.getCode());
        return callWeixinRemoteMethod(requestUrl, oauth2CodePojo);
    }

    public static Map<String, Object> callWeixinRemoteMethod(String url, Object pojo) {
        logger.debug("=====url==callWeixinRemoteMethod==>" + url);
        String response = null;

        try {
            response = WeixinUtil.getHttpsResponse(url);
        } catch (ParseException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        Map<String, Object> ruleResut = JsonUtils.jsonToMap(response);
        logger.debug("=====ruleResut====>" + ruleResut);
        return ruleResut;
    }


    public static void main(String[] args) {
        try {
            String token = "9_dm3jCuiX4YCHW6e0qvbWQlF0tEipXuHrMY7vd6xhl9V-EZ3bi4pFP-BBLoPc6cyCGtA8BK9pfv-m52AOU0WvijNvCCiZzxbf0ktw_Zf77Rf9Ej1dYmM6e1ufrRrp8VvoFxmUtzOh3k7-Ggq5SFQiACAFSD";
            String caidanjson = "{\"button\":[{\"type\":\"view\",\"name\":\"迪信优品\",\"url\":\"http://bf.wangjushijie.com/site/goods/list?sellerId=0&dis=0\"},{\"name\":\"答疑解惑\",\"sub_button\":[{\"type\":\"view\",\"name\":\"关于我们\",\"url\":\"https://v.xiumi.us/board/v5/2Y5p9/87219821\"},{\"type\":\"view\",\"name\":\"店主权益\",\"url\":\"https://d.xiumi.us/board/v5/2Y5p9/87219633\"},{\"type\":\"view\",\"name\":\"带您入门\",\"url\":\"https://v.xiumi.us/board/v5/2Y5p9/88290743\"},{\"type\":\"click\",\"name\":\"客服帮助\",\"key\":\"BJ_ONLINESERVICE\"},{\"type\":\"view\",\"name\":\"往期精彩\",\"url\":\"http://mp.weixin.qq.com/mp/homepage?__biz=MjM5NjAxODg2Mg==&hid=1&sn=b1d20e032da780816e22a7f2cd9b8613#wechat_redirect\"}]},{\"name\":\"会员中心\",\"sub_button\":[{\"type\":\"view\",\"name\":\"会员卡\",\"url\":\"http://dxtweixin.dxtmobile.com/dxt_member/a/user/receive?wxfuwuhao_id=gh_e8e5ae05f1d0\"},{\"type\":\"click\",\"name\":\"获取优惠\",\"key\":\"BJ_WEIXINCOUPON\"},{\"type\":\"click\",\"name\":\"积分兑换\",\"key\":\"BJ_VALUEEXCHANGE\"}]}]}";
            String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", token);
            String json = httpRequest(requestUrl, "GET", caidanjson);
            System.out.println("menu result: " + json);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }


}
