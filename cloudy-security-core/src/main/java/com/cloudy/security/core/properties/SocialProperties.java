package com.cloudy.security.core.properties;

/**
 * Created by ljy_cloudy on 2018/10/13.
 */
public class SocialProperties {
    private QQProperties qq = new QQProperties();
    private WeixinProperties weixin = new WeixinProperties();

    private String filterProcessesUrl = "/auth";

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public WeixinProperties getWeixin() {
        return weixin;
    }

    public void setWeixin(WeixinProperties weixin) {
        this.weixin = weixin;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
