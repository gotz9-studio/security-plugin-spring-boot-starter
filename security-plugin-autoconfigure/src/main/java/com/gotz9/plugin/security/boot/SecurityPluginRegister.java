package com.gotz9.plugin.security.boot;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SecurityPluginRegister implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Set<String> classes = new HashSet<>();

        Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(EnableSecurityPlugin.class.getName());
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(attributes);

        String storageConfiguration = "com.gotz9.plugin.security.boot.storage.SecurityPluginStorageConfiguration";

        if (annotationAttributes != null
                && annotationAttributes.getBoolean("enableStorage")) {
            classes.add(storageConfiguration);
        }

        classes.add(SecurityPluginConfiguration.class.getName());

        return classes.toArray(new String[0]);
    }

}
