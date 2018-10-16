package com.cloudy.security.core.properties;


import org.springframework.boot.autoconfigure.social.SocialProperties;
/**
 * Created by ljy_cloudy on 2018/10/14.
 */
public class WeixinProperties extends SocialProperties {
    private String providerId = "weixin";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
