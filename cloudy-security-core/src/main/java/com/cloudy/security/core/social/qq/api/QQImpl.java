package com.cloudy.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.net.URL;

/**
 * Created by ljy_cloudy on 2018/10/13.
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 获取openId
     */
    public static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    public static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?&oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String appId, String access_token) {
        super(access_token, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, access_token);
        String result = getRestTemplate().getForObject(url, String.class);

        logger.info("get openid: " + result);
        this.openId = StringUtils.substringBetween(result,"\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String str = getRestTemplate().getForObject(url, String.class);
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(str, QQUserInfo.class);
            userInfo.setOpenId(openId);
            logger.info(str);
        } catch (IOException e) {
            logger.warn("parse QQUserInfo fail ",e);
        }
        return userInfo;
    }
}
