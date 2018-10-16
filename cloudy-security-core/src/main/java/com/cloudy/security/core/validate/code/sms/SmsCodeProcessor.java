package com.cloudy.security.core.validate.code.sms;

import com.cloudy.security.core.properties.SecurityConstants;
import com.cloudy.security.core.validate.code.ValidateCode;
import com.cloudy.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * Created by ljy_cloudy on 2018/10/10.
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile =  ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
            smsCodeSender.send(mobile, validateCode.getCode());
    }
}
