package com.gotz9.plugin.security.boot;

import com.gotz9.plugin.security.boot.web.WebSecurityPluginConfigurerAdapter;
import com.gotz9.plugin.security.boot.web.WebSecurityPluginProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@ConditionalOnClass(EnableSecurityPlugin.class)
@EnableConfigurationProperties(WebSecurityPluginProperties.class)
public class SecurityPluginConfiguration {

    @Configuration
    @ConditionalOnClass(WebSecurityConfiguration.class)
    @ConditionalOnMissingBean(WebSecurityPluginConfigurerAdapter.class)
    @EnableWebSecurity
    static class DefaultWebSecurityPluginConfiguration extends WebSecurityPluginConfigurerAdapter {

    }

}