package com.cloudy.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by ljy_cloudy on 2018/10/7.
 */
@ConfigurationProperties(prefix = "cloudy.security")
public class BrowserProperties {

    private String loginPage = "/imooc-signIn.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
