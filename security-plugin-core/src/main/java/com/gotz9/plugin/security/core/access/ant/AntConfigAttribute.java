package com.gotz9.plugin.security.core.access.ant;

import org.springframework.security.access.ConfigAttribute;

import java.util.Objects;

public class AntConfigAttribute implements ConfigAttribute {

    private final String path;

    public AntConfigAttribute(String value) {
        this.path = value;
    }

    @Override
    public String getAttribute() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AntConfigAttribute that = (AntConfigAttribute) o;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

}
