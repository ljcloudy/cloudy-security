package com.cloudy.security.core.properties;

/**
 * Created by ljy_cloudy on 2018/10/10.
 */
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;

    private String url;

    public int getLength() {
        return length;
    }
    public void setLength(int lenght) {
        this.length = lenght;
    }
    public int getExpireIn() {
        return expireIn;
    }
    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
