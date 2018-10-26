package com.cloudy.security.openid;

import com.cloudy.security.core.properties.SecurityConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by lijianyun on 2018/10/18
 */
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String openidParameter = "openid";
    private String providerIdParameter = "providerId";
    private boolean postOnly = true;

    public OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID, "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String openid = this.obtainOpenid(request);
            String providerId = this.obtainProviderId(request);
            if (StringUtils.isBlank(openid)) {
                throw new IllegalArgumentException("openid is null");
            }
            if (StringUtils.isBlank(providerId)) {
                throw new IllegalArgumentException("providerId is null");
            }
            OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openid, providerId);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(this.providerIdParameter);
    }

    protected String obtainOpenid(HttpServletRequest request) {
        return request.getParameter(this.openidParameter);
    }

    protected void setDetails(HttpServletRequest request, OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setOpenidParameter(String openidParameter) {
        Assert.hasText(openidParameter, "Username parameter must not be empty or null");
        this.openidParameter = openidParameter;
    }

    public void setProviderIdParameter(String providerIdParameter) {
        Assert.hasText(providerIdParameter, "Password parameter must not be empty or null");
        this.providerIdParameter = providerIdParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getOpenidParameter() {
        return openidParameter;
    }

    public String getProviderIdParameter() {
        return providerIdParameter;
    }
}
