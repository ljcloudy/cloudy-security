package com.cloudy.config;

import com.cloudy.filter.TimerFilter;
import com.cloudy.interceptor.TimerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljy_cloudy on 2018/10/6.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Autowired
//    private TimerInterceptor timerInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timerInterceptor);
//    }

//    @Bean
    public FilterRegistrationBean timerFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimerFilter timerFilter = new TimerFilter();
        registrationBean.setFilter(timerFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;
    }
}
