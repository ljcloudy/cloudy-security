package com.cloudy.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by ljy_cloudy on 2018/10/9.
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
