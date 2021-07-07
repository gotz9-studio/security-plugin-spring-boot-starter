package com.gotz9.app.service;

import com.gotz9.plugin.security.core.access.ant.AntResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public static final Logger LOG = LoggerFactory.getLogger(TestService.class);

    @AntResource("data:read")
    public void access() {
        LOG.info("data accessed!");
    }

}
