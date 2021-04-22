package com.gotz9.plugin.security.boot.method;

import com.gotz9.plugin.security.core.access.voter.AccessDecisionManagerProxyVoter;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

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


}
