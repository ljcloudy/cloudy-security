package com.cloudy.security.core.properties;

import com.cloudy.security.core.validate.code.ImageCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by ljy_cloudy on 2018/10/7.
 */
@ConfigurationProperties(prefix = "cloudy.security")
public class SecurityProperties {


    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
