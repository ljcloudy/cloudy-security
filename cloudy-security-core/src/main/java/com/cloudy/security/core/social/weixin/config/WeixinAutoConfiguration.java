package com.cloudy.security.core.social.weixin.config;

import com.cloudy.security.core.properties.SecurityProperties;
import com.cloudy.security.core.properties.WeixinProperties;
import com.cloudy.security.core.social.CloudyConnectView;
import com.cloudy.security.core.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * Created by ljy_cloudy on 2018/10/14.
 */
@Configuration
@ConditionalOnProperty(prefix = "cloudy.security.social.weixin", name = "app-id")
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeixinProperties weixin = securityProperties.getSocial().getWeixin();
        return new WeixinConnectionFactory(weixin.getProviderId(), weixin.getAppId(), weixin.getAppSecret());
    }

    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weixinConnectedView() {
        return new CloudyConnectView();
    }

}
