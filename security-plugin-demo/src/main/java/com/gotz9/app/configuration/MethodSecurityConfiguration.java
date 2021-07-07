package com.gotz9.app.configuration;

import com.gotz9.plugin.security.boot.method.MethodSecurityPluginConfiguration;
import com.gotz9.plugin.security.core.access.ant.AntResourceAccessVoter;
import com.gotz9.plugin.security.core.access.ant.AntResourceExtractor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.annotation.SecuredAnnotationSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity
public class MethodSecurityConfiguration extends MethodSecurityPluginConfiguration {

    @Override
    protected AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new LinkedList<>();

        // 添加自定义的 voter
        decisionVoters.add(new AntResourceAccessVoter());

        return new UnanimousBased(decisionVoters);
    }

    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        return new SecuredAnnotationSecurityMetadataSource(new AntResourceExtractor()); // 使用 AntResource 解析用的 MetadataSource
    }

}
