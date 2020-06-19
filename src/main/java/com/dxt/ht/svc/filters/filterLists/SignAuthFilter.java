package com.dxt.ht.svc.filters.filterLists;


import com.alibaba.fastjson.JSONObject;
import com.dxt.ht.svc.filters.util.BodyReaderHttpServletRequestWrapper;
import com.dxt.ht.svc.filters.util.HttpUtils;
import com.dxt.ht.svc.filters.util.SignStatus;
import com.dxt.ht.svc.filters.util.SignUtil;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

/**
 * @ProjectName: airline
 * @Package: com.ticket.airline.filters.filterLists
 * @Description: 过滤器
 * @Author: Z.G.C
 * @CreateDate: 2019/9/18 9:02 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/9/18 9:02 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */

@Slf4j
@WebFilter(filterName = "SignAuthFilter ")
@Configuration
public class SignAuthFilter implements Filter {

    private static String REQUEST_METHOD = "GET";

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("filter----");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH,OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        //chain.doFilter(req, res);

        String path=request.getRequestURI();

        HttpServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        String method = requestWrapper.getMethod();
        boolean pathBool = verfilyUrl(path);
        if(pathBool||REQUEST_METHOD.equals(method)){
            chain.doFilter(requestWrapper, response);
            return ;
        }
            //获取全部参数(包括URL和body上的)
        SortedMap<String, Object> allParams = HttpUtils.getAllParams(requestWrapper);
            //对参数进行签名验证
        SignStatus signStatus = SignStatus.succeed;//SignUtil.verifySign(allParams);
        if (true) {
                System.out.println("签名通过");
                chain.doFilter(requestWrapper, response);
				return;
            } else {
                System.out.println("参数校验出错");
                //校验失败返回前端
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                JSONObject resParam = new JSONObject();
                resParam.put("msg", signStatus.getContent());
                resParam.put("status", "error");
                out.append(resParam.toJSONString());
				return;
            }
            // 多次请求 没有时间戳
    }

    @Override
    public void destroy() {

    }

    private boolean verfilyUrl(String url) {
        List<String> list = new ArrayList<String>();
        list.add("/ht-svc/api/wechat/wxPayCallBack");
        if (list.contains(url)) {
            return true;
        }
        return false;


    }
}
