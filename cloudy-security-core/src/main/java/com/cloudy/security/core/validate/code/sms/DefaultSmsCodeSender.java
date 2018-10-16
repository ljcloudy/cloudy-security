package com.cloudy.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ljy_cloudy on 2018/10/10.
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String mobile, String code) {
        logger.info("向手机" + mobile + "发送短信验证码: " + code);
    }
}
