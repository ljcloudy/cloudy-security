package com.cloudy.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by ljy_cloudy on 2018/10/10.
 */
public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     * @param servletWebRequest
     */
    void validate(ServletWebRequest servletWebRequest);
}
