package com.cloudy.security.browser;

import com.cloudy.security.core.authentication.AbstractChannelSecurityConfig;
import com.cloudy.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cloudy.security.core.properties.SecurityConstants;
import com.cloudy.security.core.properties.SecurityProperties;
import com.cloudy.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Created by ljy_cloudy on 2018/10/7.
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;


    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig).and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .apply(springSocialConfigurer)
                    .and()
                        .rememberMe()
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                        .userDetailsService(userDetailsService)
                .and()
                    .authorizeRequests()
                    .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                            SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                            securityProperties.getBrowser().getLoginPage(),
                            securityProperties.getBrowser().getSignUpUrl(),
                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                            "/user/regist")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .csrf().disable();
    }
}
