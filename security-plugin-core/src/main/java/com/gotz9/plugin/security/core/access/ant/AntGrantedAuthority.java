package com.gotz9.plugin.security.core.access.ant;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class AntGrantedAuthority implements GrantedAuthority {

    private final String path;

    public AntGrantedAuthority(String expr) {
        this.path = expr;
    }

    @Override
    public String getAuthority() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AntGrantedAuthority that = (AntGrantedAuthority) o;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

}
