package com.gotz9.plugin.security.core.access.ant;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.annotation.AnnotationMetadataExtractor;

import java.util.ArrayList;
import java.util.Collection;

public class AntResourceExtractor implements AnnotationMetadataExtractor<AntResource> {

    @Override
    public Collection<? extends ConfigAttribute> extractAttributes(AntResource annotation) {
        String[] values = annotation.value();

        ArrayList<AntConfigAttribute> attributes = new ArrayList<>(values.length);
        for (String value : values) {
            attributes.add(new AntConfigAttribute(value));
        }

        return attributes;
    }

}
