package com.cloudy.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by ljy_cloudy on 2018/10/8.
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
