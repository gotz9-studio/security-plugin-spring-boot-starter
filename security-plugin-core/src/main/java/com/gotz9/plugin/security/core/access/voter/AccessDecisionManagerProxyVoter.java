package com.gotz9.plugin.security.core.access.voter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public class AccessDecisionManagerProxyVoter implements AccessDecisionVoter<Object> {

    private final AccessDecisionManager proxyTarget;

    public AccessDecisionManagerProxyVoter(AccessDecisionManager proxyTarget) {
        this.proxyTarget = proxyTarget;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return proxyTarget.supports(attribute);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return proxyTarget.supports(clazz);
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        try {
            proxyTarget.decide(authentication, object, attributes);
            return AccessDecisionVoter.ACCESS_GRANTED;
        } catch (AccessDeniedException authenticationException) {
            return AccessDecisionVoter.ACCESS_DENIED;
        }
    }
}