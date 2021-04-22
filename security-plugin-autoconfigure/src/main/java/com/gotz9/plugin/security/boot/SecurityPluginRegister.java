package com.gotz9.plugin.security.boot;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class SecurityPluginRegister implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{SecurityPluginConfiguration.class.getName()};
    }

}
