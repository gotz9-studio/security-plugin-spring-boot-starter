package com.gotz9.plugin.security.core.access.ant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AntResource {

    String[] value();

}
