package com.cloudy.security.core.properties;

/**
 * Created by ljy_cloudy on 2018/10/23.
 */
public class OAuth2Properties {

    private String signingKey = "cloudy";


    private OAuth2ClientProperties[] clients = {};

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }
}
