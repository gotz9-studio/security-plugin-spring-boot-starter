package com.gotz9.app;

import com.gotz9.app.service.TestService;
import com.gotz9.plugin.security.core.access.ant.AntGrantedAuthority;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
public class AppTester {

    public static final Logger LOG = LoggerFactory.getLogger(AppTester.class);

    public Authentication antResourceUser() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new AntGrantedAuthority("data:*"));

        return new UsernamePasswordAuthenticationToken("antResourceUser", "passwd", authorities);
    }

    public Authentication normalUser() {
        return new UsernamePasswordAuthenticationToken("normalUser", "passwd", Collections.emptyList());
    }

    @Autowired
    private TestService testService;

    @Test
    public void accessWithAuthorityTest() {
        SecurityContextHolder.getContext().setAuthentication(antResourceUser());
        try {
            testService.access();
        } catch (Exception e) {
            LOG.error("access", e);
            Assert.fail();
        }
    }

    @Test
    public void accessWithoutAuthorityTest() {
        SecurityContextHolder.getContext().setAuthentication(normalUser());
        try {
            testService.access();
            Assert.fail();
        } catch (Exception e) {
            LOG.error("access", e);
        }
    }

}
