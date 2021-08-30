package com.gotz9.plugin.security.boot.method;

import com.gotz9.plugin.security.core.access.AccessDecisionManagerProxyVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MethodSecurityPluginConfiguration extends GlobalMethodSecurityConfiguration {

    protected List<? extends AccessDecisionVoter<?>> customVoters() {
        return Collections.emptyList();
    }

    @Override
    protected AccessDecisionManager accessDecisionManager() {

        ArrayList<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();

        AccessDecisionManagerProxyVoter proxyVoter = new AccessDecisionManagerProxyVoter(super.accessDecisionManager());
        decisionVoters.add(proxyVoter);

        decisionVoters.addAll(customVoters());

        return new UnanimousBased(decisionVoters);
    }

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Sub classes can override this method to register different types of authentication.
     * If not overridden, {@link #configure(AuthenticationManagerBuilder)} will attempt to
     * autowire by type.
     *
     * @param auth the {@link AuthenticationManagerBuilder} used to register different
     *             authentication mechanisms for the global method security.
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(new DaoAuthenticationProvider())
                .userDetailsService(userDetailsService)
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

}
