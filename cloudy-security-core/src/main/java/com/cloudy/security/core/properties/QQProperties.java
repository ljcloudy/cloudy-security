package com.cloudy.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Created by ljy_cloudy on 2018/10/13.
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
