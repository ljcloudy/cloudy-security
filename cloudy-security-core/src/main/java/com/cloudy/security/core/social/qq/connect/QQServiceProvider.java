package com.cloudy.security.core.social.qq.connect;

import com.cloudy.security.core.social.qq.api.QQ;
import com.cloudy.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

import java.net.URL;

/**
 * Created by ljy_cloudy on 2018/10/13.
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    public static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    public static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {

        return new QQImpl(appId, accessToken);
    }
}
