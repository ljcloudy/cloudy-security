package com.cloudy.security.core.social.weixin.connect;

import com.cloudy.security.core.social.weixin.api.Weixin;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * Created by ljy_cloudy on 2018/10/14.
 */
public class WeixinConnectionFactory extends OAuth2ConnectionFactory<Weixin> {
    /**
     *
     * @param providerId
     * @param appId
     * @param appSecret
     */
    public WeixinConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new WeixinServiceProvider(appId,appSecret), new WeixinAdapter());
    }
}
