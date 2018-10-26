package com.cloudy.security.support;

import org.springframework.social.security.SocialUserDetails;

import java.io.Serializable;

/**
 * Created by ljy_cloudy on 2018/10/18.
 */
public class SocialUserInfo {

    private String providerId;

    private String providerUserId;

    private String nickName;

    private String headimg;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
}
