package com.cloudy.security.openid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.HashSet;
import java.util.Set;

/**
 * created by lijianyun on 2018/10/18
 */
public class OpenIdAuthenticationProvider implements AuthenticationProvider {
    private SocialUserDetailsService socialUserDetailsService;
    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OpenIdAuthenticationToken openIdAuthenticationToken = (OpenIdAuthenticationToken) authentication;
        Set<String> openidSet = new HashSet<>();
        openidSet.add((String) openIdAuthenticationToken.getPrincipal());
        Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(openIdAuthenticationToken.getProviderId(), openidSet);
        if (CollectionUtils.isEmpty(userIds) || userIds.size() != 1) {
            throw new InternalAuthenticationServiceException("无法获取用户信息！");
        }
        String userId = userIds.iterator().next();
        SocialUserDetails socialUserDetails = socialUserDetailsService.loadUserByUserId(userId);

        if (socialUserDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息！");
        }
        OpenIdAuthenticationToken authenticationToken = new OpenIdAuthenticationToken(socialUserDetails, socialUserDetails.getAuthorities());
        authenticationToken.setDetails(authentication.getDetails());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public SocialUserDetailsService getSocialUserDetailsService() {
        return socialUserDetailsService;
    }

    public void setSocialUserDetailsService(SocialUserDetailsService socialUserDetailsService) {
        this.socialUserDetailsService = socialUserDetailsService;
    }

    public UsersConnectionRepository getUsersConnectionRepository() {
        return usersConnectionRepository;
    }

    public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository) {
        this.usersConnectionRepository = usersConnectionRepository;
    }
}
