package com.gotz9.plugin.security.core.access.ant;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class AntResourceAccessVoter implements AccessDecisionVoter<Object> {

    public static final String DEFAULT_SEPARATOR = ":";

    public final AntPathMatcher matcher;

    public AntResourceAccessVoter() {
        this(DEFAULT_SEPARATOR);
    }

    public AntResourceAccessVoter(String separator) {
        this.matcher = new AntPathMatcher(separator);
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return attribute instanceof AntConfigAttribute;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MethodInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        AntConfigAttribute attribute = findConfigAttribute(attributes);

        if (attribute == null)
            return ACCESS_ABSTAIN;

        Collection<AntGrantedAuthority> antAuthorities = findAntAuthorities(authentication.getAuthorities());

        for (AntGrantedAuthority antAuthority : antAuthorities) {
            if (matcher.match(antAuthority.getAuthority(), attribute.getAttribute()))
                return ACCESS_GRANTED;
        }

        return ACCESS_DENIED;
    }

    private AntConfigAttribute findConfigAttribute(Collection<ConfigAttribute> attributes) {
        if (attributes != null) {
            for (ConfigAttribute attribute : attributes) {
                if (attribute instanceof AntConfigAttribute) {
                    return (AntConfigAttribute) attribute;
                }
            }
        }
        return null;
    }

    private Collection<AntGrantedAuthority> findAntAuthorities(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null || authorities.size() <= 0)
            return Collections.emptyList();

        return authorities.stream().filter(e -> e instanceof AntGrantedAuthority).map(e -> (AntGrantedAuthority) e).collect(Collectors.toList());
    }


}