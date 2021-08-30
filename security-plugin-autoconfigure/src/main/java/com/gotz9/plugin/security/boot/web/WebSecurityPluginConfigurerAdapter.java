package com.gotz9.plugin.security.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class WebSecurityPluginConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    protected WebSecurityPluginProperties properties;

    protected Collection<String> permittedUri() {
        return properties.getPermittedUri().stream()
                .filter(s -> Objects.nonNull(s) && !"".equals(s))
                .collect(Collectors.toList());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configPermittedUri(http);

        configHttpSecurity(http);
    }

    private void configPermittedUri(HttpSecurity http) throws Exception {
        Collection<String> permittedUri = permittedUri();

        if (permittedUri != null && permittedUri.size() > 0) {
            http.authorizeRequests()
                    .antMatchers(permittedUri.toArray(new String[0])).permitAll();
        }

        http.authorizeRequests()
                .anyRequest().authenticated();
    }

    /**
     * 在一些基础配置之后调用, 对 HttpSecurity 进行配置, 承接 {@link #configure(HttpSecurity)}.
     */
    protected void configHttpSecurity(HttpSecurity http) throws Exception {
    }

}
