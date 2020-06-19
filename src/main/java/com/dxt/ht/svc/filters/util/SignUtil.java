package com.dxt.ht.svc.filters.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import wiremock.org.eclipse.jetty.util.StringUtil;
import wiremock.org.eclipse.jetty.util.security.Credential;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ProjectName: airline
 * @Package: com.ticket.airline.filters.util
 * @Description: java类作用描述
 * @Author: Z.G.C
 * @CreateDate: 2019/9/18 1:42 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/9/18 1:42 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class SignUtil {

    private static final int MAX_OUT_TIME = 300000;
    private static final String WX_KEY = "0Hind9iRVt0WhQwywVHqeqHiSELUW3sF";

    /**
     * @param params
     *            所有的请求参数都会在这里进行排序加密
     * @return 验证签名结果
     */
    public static SignStatus verifySign(SortedMap<String, Object> params) {
        String urlSign = (String) params.get("sign");
        if (!checkParameter(params)) return SignStatus.params_error;
        if (isOutTime(params)) return SignStatus.time_out;
        String key = getKey(params);
        // 把参数加密
        String paramsSign = getParamsSign(params,key);
        //判断前端传回参数是否与本地验签一致
        boolean bl = !StringUtils.isEmpty(paramsSign) && urlSign.equals(paramsSign);

        return bl?SignStatus.succeed:SignStatus.sign_fail;
    }

    /**
     * 检查入参是否合格
     * @param params
     * @return  true：检查通过   false：检查失败
     */
    private static boolean checkParameter(SortedMap<String, Object> params) {
        if (StringUtils.isEmpty(params.get("sign"))) {
            return false;
        }
        if(!(params.get("timeStamp") instanceof Long)){
            return false;
        }
        if(StringUtils.isEmpty(params.get("source"))){
            return false;
        }
        return true;
    }

    /**
     *
     * @param params
     * @return
     */
    private static String getKey(SortedMap<String, Object> params) {
        String source = (String) params.get("source");
        String key = null;
        if ("wx".equals(source)){
            key = WX_KEY;
        }
        return key;
    }

    /**
     * 是否超时
     * @param params
     * @return true超时
     */
    private static boolean isOutTime(SortedMap<String, Object> params) {
        Long timeStamp = (Long) params.get("timeStamp");
        Long nowTimeStamp = System.currentTimeMillis();
        if (nowTimeStamp-timeStamp>MAX_OUT_TIME){
            return true;
        }
        return false;
    }


    /**
     * @param params
     *            所有的请求参数都会在这里进行排序加密
     * @return 得到签名
     */
    private static String  getParamsSign(SortedMap<String, Object> params,String key){
        params.remove("sign");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> e : params.entrySet()) {

            if (e.getValue()!=null) // 参数值为空，则不参与签名
                sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
        }
        sb.append("key=").append(key);
       return DigestUtils.md5DigestAsHex(sb.toString().getBytes()).toUpperCase();
    }


    public static void main(String[] args) {
        SortedMap<String, Object> result = new TreeMap<>();
        result.put("name","asd");
        result.put("phone","123123213123");

        result.put("timeStamp",System.currentTimeMillis()-MAX_OUT_TIME-500);
        result.put("source","wx");
        result.put("sign",getParamsSign(result,"abc"));
        String mad  = Credential.MD5.digest("看家垃圾分类").toUpperCase();
             String md5  = DigestUtils.md5DigestAsHex(StringUtil.getUtf8Bytes("看家垃圾分类")).toUpperCase();
        System.out.println(mad+"-----"+md5);
    }
}
