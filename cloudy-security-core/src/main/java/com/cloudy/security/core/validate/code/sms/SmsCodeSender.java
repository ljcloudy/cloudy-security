package com.cloudy.security.core.validate.code.sms;

/**
 * Created by ljy_cloudy on 2018/10/10.
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
