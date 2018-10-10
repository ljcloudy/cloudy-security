package com.cloudy.security.core.validate.code.sms;

/**
 * Created by ljy_cloudy on 2018/10/10.
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送短信验证码" + code);
    }
}
