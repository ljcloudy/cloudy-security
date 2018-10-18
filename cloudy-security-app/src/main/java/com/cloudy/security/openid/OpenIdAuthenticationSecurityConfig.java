package com.cloudy.security.openid;

import com.cloudy.security.handler.FailHandler;
import com.cloudy.security.handler.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * created by lijianyun on 2018/10/18
 */
@Component
public class OpenIdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private SuccessHandler successHandler;
    @Autowired
    private FailHandler failHandler;
    @Autowired
    private SocialUserDetailsService socialUserDetailsService;
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        OpenIdAuthenticationFilter openIdAuthenticationFilter = new OpenIdAuthenticationFilter();
        openIdAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        openIdAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        openIdAuthenticationFilter.setAuthenticationFailureHandler(failHandler);

        OpenIdAuthenticationProvider provider = new OpenIdAuthenticationProvider();
        provider.setSocialUserDetailsService(socialUserDetailsService);
        provider.setUsersConnectionRepository(usersConnectionRepository);

        http.authenticationProvider(provider).addFilterAfter(openIdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
