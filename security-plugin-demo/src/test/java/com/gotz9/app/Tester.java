package com.gotz9.app;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.util.AntPathMatcher;

public class Tester {

    @Test
    public void test() {
        System.out.println(MethodInvocation.class.isAssignableFrom(ReflectiveMethodInvocation.class));
    }

    @Test
    public void antMatcherTest() {
        AntPathMatcher matcher = new AntPathMatcher(":");

        Assert.assertFalse(matcher.match("test:data", "test:data:op"));
    }

}
