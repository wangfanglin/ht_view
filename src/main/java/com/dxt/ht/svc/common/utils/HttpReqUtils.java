package com.dxt.ht.svc.common.utils;



import com.alibaba.fastjson.JSON;
import com.dxt.ht.svc.apply.entity.ReportCaseParameter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @ProjectName: airline
 * @Package: com.ticket.airline.Utils
 * @Description: java类作用描述
 * @Author: Z.G.C
 * @CreateDate: 2019/9/19 3:05 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/9/19 3:05 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class HttpReqUtils {
    public static final String US_ASCII = "US-ASCII";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String UTF_8 = "UTF-8";
    public static final String UTF_16BE = "UTF-16BE";
    public static final String UTF_16LE = "UTF-16LE";
    public static final String UTF_16 = "UTF-16";
    public static final String GBK = "GBK";

    public HttpReqUtils() {
    }

    public static String doPost(String url, Map<String, Object> map) throws Exception {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        httpClient = new SSLClient();
        httpPost = new HttpPost(url);
        List<NameValuePair> list = new ArrayList();
        Iterator iterator = map.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<String, String> elem = (Map.Entry)iterator.next();
            list.add(new BasicNameValuePair((String)elem.getKey(), (String)elem.getValue()));
        }

        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
            httpPost.setEntity(entity);
        }

        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, "UTF-8");
            }
        }

        return result;
    }

    public static String doGet(String url) throws Exception {
        HttpClient httpClient = null;
        String result = null;
        HttpGet httpget = null;
        httpClient = new SSLClient();
        httpget = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpget);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, "UTF-8");
            }
        }

        return result;
    }
    public static HttpResponse doGetParam(String host, String path, String method, Map<String, String> headers, Map<String, String> querys) throws Exception {
        HttpClient httpClient = wrapClient(host);
        HttpGet request = new HttpGet(buildUrl(host, path, querys));
        Iterator var7 = headers.entrySet().iterator();

        while(var7.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)var7.next();
            request.addHeader((String)e.getKey(), (String)e.getValue());
        }

        return httpClient.execute(request);
    }

    private static HttpClient wrapClient(String host) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        if (host.startsWith("https://")) {
            sslClient(httpClient);
        }

        return httpClient;
    }
    private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                }
            };
            ctx.init((KeyManager[])null, new TrustManager[]{tm}, (SecureRandom)null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException var6) {
            throw new RuntimeException(var6);
        } catch (NoSuchAlgorithmException var7) {
            throw new RuntimeException(var7);
        }
    }
    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isBlank(path)) {
            sbUrl.append(path);
        }

        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            Iterator var5 = querys.entrySet().iterator();

            while(var5.hasNext()) {
                Map.Entry<String, String> query = (Map.Entry)var5.next();
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }

                if (StringUtils.isBlank((String)query.getKey()) && !StringUtils.isBlank((String)query.getValue())) {
                    sbQuery.append((String)query.getValue());
                }

                if (!StringUtils.isBlank((String)query.getKey())) {
                    sbQuery.append((String)query.getKey());
                    if (!StringUtils.isBlank((String)query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode((String)query.getValue(), "utf-8"));
                    }
                }
            }

            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            System.out.println(urlNameString + "........");
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }



    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("消息发送结果： " + result);
        return result;
    }

    /**
     * 获取微信token值
     *
     * @return 微信token
     */
    public static String getWxToken (){
        try {
            String s = sendGet("http://testht.site.hollardchina.com.cn/wx-token-factory/api/account/HTXT/get", null);
            HashMap hashMap = JSON.parseObject(s,HashMap.class);
            return hashMap.get("data").toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，json字符串
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostJson(String url, String param, String token) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //渤海请求 需要在头部加token
            conn.setRequestProperty("token", token);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("消息发送结果： " + result);
        return result;
    }


}
