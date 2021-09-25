package com.gotz9.app.configuration;

import com.gotz9.plugin.security.boot.web.WebSecurityPluginConfigurerAdapter;
import com.gotz9.plugin.security.boot.web.WebSecurityPluginProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableConfigurationProperties(WebSecurityPluginProperties.class)
public class WebSecurityConfiguration extends WebSecurityPluginConfigurerAdapter {

    protected WebSecurityConfiguration(WebSecurityPluginProperties properties) {
        super(properties);
    }

    /**
     * 在一些基础配置之后调用, 对 HttpSecurity 进行配置, 承接 {@link #configure(HttpSecurity)}.
     *
     * @param http
     */
    @Override
    protected void configHttpSecurity(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .csrf().disable();
    }
}
