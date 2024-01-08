package com.ljnewmap.modules.security.config;

import com.ljnewmap.common.enums.WebFilterOrderEnum;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;


/**
 * Filter配置
 *
 */
@Configuration
public class ShiroFilterConfig {

    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        registration.setOrder(WebFilterOrderEnum.SHIRO_FILTER);
        registration.addUrlPatterns("/*");
        return registration;
    }


}
