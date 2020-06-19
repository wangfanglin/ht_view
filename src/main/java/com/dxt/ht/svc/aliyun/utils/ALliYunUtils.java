package com.dxt.ht.svc.aliyun.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dxt.ht.svc.aliyun.entity.IdCardInformation;
import com.dxt.ht.svc.common.utils.HttpReqUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.codec.binary.Base64.encodeBase64;


public class ALliYunUtils {
    /**
     * 使用APPCODE进行云市场ocr服务接口调用
     */
        /*
         * 获取参数的json对象
         */
        public static JSONObject getParam(int type, String dataValue) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("dataType", type);
                obj.put("dataValue", dataValue);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return obj;
        }

    /**
     * 身份证识别方法
     *
     * @param fileImgPath 身份证图片路径
     * @param faceOrBack  身份证正反面
     * @return
     */
        public static IdCardInformation gets(String fileImgPath, String faceOrBack) throws Exception {
            String host = "http://dm-51.data.aliyun.com";
            String path = "/rest/160601/ocr/ocr_idcard.json";
            String appcode = "8aefe548e07342c19bd85bd0cc11e9f8";
            //如果文档的输入中含有inputs字段，设置为True， 否则设置为False
            Boolean is_old_format = false;
            //请根据线上文档修改configure字段
            JSONObject configObj = new JSONObject();
            configObj.put("side", faceOrBack);
            String config_str = configObj.toString();
            String method = "POST";
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + appcode);
            Map<String, String> querys = new HashMap<String, String>();
            // 拼装请求body的json字符串
            JSONObject requestObj = new JSONObject();
            try {
                if(is_old_format) {
                    JSONObject obj = new JSONObject();
                    obj.put("image", fileImgPath);
                    if(config_str.length() > 0) {
                        obj.put("configure", getParam(50, config_str));
                    }
                    JSONArray inputArray = new JSONArray();
                    inputArray.add(obj);
                    requestObj.put("inputs", inputArray);
                }else{
                    requestObj.put("image", fileImgPath);
                    if(config_str.length() > 0) {
                        requestObj.put("configure", config_str);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String bodys = requestObj.toString();
            IdCardInformation information = new IdCardInformation();
            try {
                /**
                 * 重要提示如下:
                 * HttpUtils请从
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
                 * 下载
                 *
                 * 相应的依赖请参照
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
                 */
                HttpResponse response = HttpUtil.doPost(host, path, method, headers, querys, bodys);
                int stat = response.getStatusLine().getStatusCode();
                if(stat != 200){
                    System.out.println("Http code: " + stat);
                    System.out.println("http header error msg: "+ response.getFirstHeader("X-Ca-Error-Message"));
                    System.out.println("Http body error msg:" + EntityUtils.toString(response.getEntity()));
                    return null;
                }

                String res = EntityUtils.toString(response.getEntity());
                JSONObject res_obj = JSON.parseObject(res);
                if(is_old_format) {
                    JSONArray outputArray = res_obj.getJSONArray("outputs");
                    String output = outputArray.getJSONObject(0).getJSONObject("outputValue").getString("dataValue");
                    JSONObject out = JSON.parseObject(output);
                    information = JSONObject.toJavaObject(out, IdCardInformation.class);
                }else{
                    System.out.println(res_obj.toJSONString());
                    information = JSONObject.toJavaObject(res_obj, IdCardInformation.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return information;
        }

    /**
     * 通过url 来获取图片Base64字符串
     * @param urlPath
     * @return
     */
    public static String getBase64ByUrl(String urlPath){
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            URL url = new URL(urlPath);
            byte[] by = new byte[1024];
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout(1000*5);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            int len = -1;
            while ( (len = inputStream.read(by)) !=-1){
                data.write(by,0,len);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getMimeEncoder().encodeToString(data.toByteArray());
        //return Base64.getEncoder().encodeToString(data.toByteArray());
    }

    /**
     * 银行卡识别
     *
     * @return
     */
    public static Map getBankCardIdentification(String acct_no, String idcard,String name){
        String host = "http://yhkr.market.alicloudapi.com";
        String path = "/communication/personal/1886";
        String method = "POST";
        String appcode = "8aefe548e07342c19bd85bd0cc11e9f8";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("acct_no", acct_no);
        bodys.put("idcard", idcard);
        bodys.put("name", name);
        try {
            HttpResponse response = HttpUtil.doPost(host, path, method, headers, querys, bodys);
            String s = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(s,HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 银行卡识别信息
     *
     * @param url
     * @return
     */
    public static Map getBankCardIdentification(String url){
        String host = "https://yhk.market.alicloudapi.com";
        String path = "/rest/160601/ocr/ocr_bank_card.json";
        String method = "POST";
        String appcode = "8aefe548e07342c19bd85bd0cc11e9f8";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        String bodys = "{\"image\":\""+url+"\"}";
        try {
            HttpResponse response = HttpUtil.doPost(host, path, method, headers, querys, bodys);
            String string = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(string,HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
