package com.cloudy.security.handler;

import com.cloudy.security.support.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ljy_cloudy on 2018/10/7.
 */
@Component
public class FailHandler implements AuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("认证失败");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
    }
}
