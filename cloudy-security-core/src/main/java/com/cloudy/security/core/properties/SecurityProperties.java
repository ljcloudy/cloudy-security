package com.cloudy.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by ljy_cloudy on 2018/10/7.
 */
@ConfigurationProperties(prefix = "cloudy.security")
public class SecurityProperties {
    private BrowserProperties browserProperties = new BrowserProperties();

    public BrowserProperties getBrowserProperties() {
        return browserProperties;
    }

    public void setBrowserProperties(BrowserProperties browserProperties) {
        this.browserProperties = browserProperties;
    }
}
