package com.personal.biz.configuration;

import com.personal.common.base.filter.LoggerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.regex.Pattern;

/**
 * project: com.personal.ssm
 *
 * @author zhenghanbin time: 2018/10/25 17:23
 */
@Configuration
public class FilterConfiguration {

    @Bean
    @Order(Integer.MIN_VALUE)
    public FilterRegistrationBean filter1() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(loggerFilter());
        registration.addUrlPatterns("*.do");
        registration.setName("loggerFilter");
        return registration;
    }

    @Bean(name = "loggerFilter")
    public Filter loggerFilter() {
        return new LoggerFilter();
    }
}
