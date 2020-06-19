package com.dxt.ht.svc.filters.util;

import com.alibaba.fastjson.JSONObject;
import com.dxt.pass.util.GsonUtils;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ProjectName: airline
 * @Package: com.ticket.airline.filters.util
 * @Description: java类作用描述
 * @Author: Z.G.C
 * @CreateDate: 2019/9/18 1:43 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/9/18 1:43 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class HttpUtils {

    public static final String PARAM_TYPE = "application/json";
    /**
     * 将URL的参数和body参数合并
     *
     * @author show
     * @date 14:24 2019/5/29
     * @param request
     */
    public static SortedMap<String, Object> getAllParams(HttpServletRequest request) throws IOException {
        SortedMap<String, Object> result = new TreeMap<>();
        // 获取URL上的参数
        getUrlParams(request, result);
        // 获取body参数
       /* System.out.println(request.getHeader("content-type").toLowerCase());
        if (PARAM_TYPE.equals(request.getHeader("content-type").toLowerCase())){*/
        getAllRequestParam(request, result);
        System.out.println("111111"+result.toString());
        //}
        return result;
    }

    /**
     * 获取 Body 参数
     *
     * @author show
     * @date 15:04 2019/5/30
     * @param request
     */
    public static void getAllRequestParam(final HttpServletRequest request, SortedMap<String, Object> result)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String str = "";
        StringBuilder wholeStr = new StringBuilder();
        // 一行一行的读取body体里面的内容；
        while ((str = reader.readLine()) != null) {
            wholeStr.append(str);
        }
        wholeStr.trimToSize();
        String s = wholeStr.toString();
        if (!StringUtils.isEmpty(s)) {
            // 转化成json对象
            Map<String, Object> allRequestParam = JSONObject.parseObject(s, Map.class);
            // 将URL的参数和body参数进行合并
            for (Map.Entry entry : allRequestParam.entrySet()) {
                result.put((String)entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 获取url参数
     *
     * @author show
     * @param request
     */
    public static void getUrlParams(HttpServletRequest request, SortedMap<String, Object> result) {
        String param = "";
        try {
            String urlParam = request.getQueryString();
            if (urlParam != null) {
                param = URLDecoder.decode(urlParam, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] params = param.split("&");
        for (String s : params) {
            int index = s.indexOf("=");
            if (index != -1) {
                result.put(s.substring(0, index), s.substring(index + 1));
            }
        }
    }

}
