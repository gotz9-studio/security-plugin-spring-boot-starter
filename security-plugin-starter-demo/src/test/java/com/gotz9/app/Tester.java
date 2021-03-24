package com.gotz9.app;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.framework.ReflectiveMethodInvocation;

public class Tester {

    @Test
    public void test() {
        System.out.println(MethodInvocation.class.isAssignableFrom(ReflectiveMethodInvocation.class));
    }

}
