package com.gotz9.plugin.security.boot;

import com.gotz9.plugin.security.boot.method.MethodSecurityPluginConfiguration;
import com.gotz9.plugin.security.boot.method.MethodSecurityPluginProperties;
import com.gotz9.plugin.security.boot.web.WebSecurityPluginConfigurerAdapter;
import com.gotz9.plugin.security.boot.web.WebSecurityPluginProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@ConditionalOnClass(EnableSecurityPlugin.class)
public class SecurityPluginConfiguration {

    @Configuration
    @ConditionalOnClass(WebSecurityConfiguration.class)
    @ConditionalOnMissingBean(WebSecurityPluginConfigurerAdapter.class)
    @EnableConfigurationProperties(WebSecurityPluginProperties.class)
    @EnableWebSecurity
    static class DefaultWebSecurityPluginConfiguration extends WebSecurityPluginConfigurerAdapter {

        DefaultWebSecurityPluginConfiguration(WebSecurityPluginProperties properties) {
            super(properties);
        }

    }

    @Configuration
    @ConditionalOnClass(GlobalMethodSecurityConfiguration.class)
    @ConditionalOnMissingBean(GlobalMethodSecurityConfiguration.class)
    @EnableConfigurationProperties(MethodSecurityPluginProperties.class)
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    static class DefaultMethodSecurityPluginConfiguration extends MethodSecurityPluginConfiguration {

        DefaultMethodSecurityPluginConfiguration(UserDetailsService userDetailsService) {
            super(userDetailsService);
        }

    }

}
