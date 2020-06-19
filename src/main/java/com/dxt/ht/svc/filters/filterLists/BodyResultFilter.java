package com.dxt.ht.svc.filters.filterLists;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: airline
 * @Package: com.ticket.airline.filters.filterLists
 * @Description: java类作用描述
 * @Author: Z.G.C
 * @CreateDate: 2019/10/8 10:58 AM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/10/8 10:58 AM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Slf4j
//@RestControllerAdvice
public class BodyResultFilter implements ResponseBodyAdvice {
    private final ObjectMapper objectMapper = new ObjectMapper();

//
//    @Autowired
//    BodyListProperties bodyListProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {

        //获取当前处理请求的controller的方法
        String methodName=returnType.getMethod().getName();
        // 不拦截/不需要处理返回值 的方法

//        System.out.println(bodyListProperties.getControllerMethodList());
//        //  接口文档 不走body 验签
//        List<String> methodLists = bodyListProperties.getControllerMethodList();

//        if(methodLists.contains(methodName))
//            return false;
        return returnType.hasMethodAnnotation(ResponseBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println(body);
        if (null != body) {
            try {
                Map map = objectMapper.readValue(objectMapper.writeValueAsString(body), Map.class);
                String json= JSON.toJSONString(map);


                return map;
            } catch (IOException e) {
            }
        }

        return body;
    }
}
