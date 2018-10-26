package com.cloudy.security.social;

import com.cloudy.security.core.properties.SecurityConstants;
import com.cloudy.security.core.social.CloudySpringSocialConfigurer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by ljy_cloudy on 2018/10/18.
 */
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(StringUtils.equals(beanName,"cloudySpringSocialConfigurer")){
            CloudySpringSocialConfigurer config = (CloudySpringSocialConfigurer) bean;
//            config.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
            config.signupUrl("/social/signUp");
            return config;
        }
        return bean;
    }
}
