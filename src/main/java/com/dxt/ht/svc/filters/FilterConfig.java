package com.dxt.ht.svc.filters;

import com.dxt.ht.svc.filters.filterLists.SignAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: auction
 * @Package: com.dxt.auction.filters
 * @Description: 过滤器配置
 * @Author: Z.G.C
 * @CreateDate: 2019/4/30 4:11 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/4/30 4:11 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean svcFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new SignAuthFilter());
        //拦截规则
        registration.addUrlPatterns("/api/*");
        //过滤器名称
        registration.setName("SignAuthFilter");
        //过滤器顺序 spring boot 会按照order值的大小，从小到大的顺序来依次过滤。

        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }
}
