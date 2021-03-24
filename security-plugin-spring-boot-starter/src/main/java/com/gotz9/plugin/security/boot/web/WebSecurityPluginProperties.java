package com.gotz9.plugin.security.boot.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

@ConfigurationProperties(prefix = "com.gotz9.security.web")
public class WebSecurityPluginProperties {

    private List<String> permittedUri = Collections.emptyList();

    public List<String> getPermittedUri() {
        return permittedUri;
    }

    public void setPermittedUri(List<String> permittedUri) {
        this.permittedUri = permittedUri;
    }
}
