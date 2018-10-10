package com.cloudy.security.core.validate.code.sms;

import com.cloudy.security.core.properties.SecurityProperties;
import com.cloudy.security.core.validate.code.ValidateCode;
import com.cloudy.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by ljy_cloudy on 2018/10/10.
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());

        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }
}
