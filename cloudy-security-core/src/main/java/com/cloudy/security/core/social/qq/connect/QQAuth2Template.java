package com.cloudy.security.core.social.qq.connect;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * Created by ljy_cloudy on 2018/10/13.
 */
public class QQAuth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public QQAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String resultStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        logger.info("获取accessToken的响应: " + resultStr);

        AccessGrant accessGrant = null;
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(resultStr, "&");

        if (ArrayUtils.isNotEmpty(items) && items.length == 3) {
            String accessToken = StringUtils.substringAfterLast(items[0], "=");
            Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
            String refreshToken = StringUtils.substringAfter(items[2], "=");

            accessGrant = new AccessGrant(accessToken, null, refreshToken, expiresIn);
        }
        return accessGrant;
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
