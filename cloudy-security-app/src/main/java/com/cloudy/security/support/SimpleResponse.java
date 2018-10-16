package com.cloudy.security.support;

/**
 * Created by ljy_cloudy on 2018/10/16.
 */
public class SimpleResponse {

    private Object response;

    public SimpleResponse(String response) {
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
