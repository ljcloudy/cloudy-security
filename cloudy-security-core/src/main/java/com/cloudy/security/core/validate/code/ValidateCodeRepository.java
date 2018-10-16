package com.cloudy.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by ljy_cloudy on 2018/10/16.
 */
public interface ValidateCodeRepository {
    /**
     * 保存验证码
     * @param request
     * @param code
     * @param codeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType codeType);

    /**
     * 获取短信验证码
     * @param request
     * @param codeType
     * @return
     */
    ValidateCode get(ServletWebRequest request,ValidateCodeType codeType);

    /**
     * 移除验证码
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);
}
