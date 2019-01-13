package com.fancychuan.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect // 加上这个注释以后才方便被框架扫描到
public class LoggingAspect {

    // execution是切入点表达式
    @Before("execution(public * com.fancychuan.spring.aop.aspectj.CustomerBo.addCustomer(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[x]正在跑logBefore()...");
        System.out.println("[x]hijacked: " + joinPoint.getSignature().getName());
        System.out.println("[x]************");
    }

    @After("execution(public * com.fancychuan.spring.aop.aspectj.CustomerBo.deleteCustomer(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[x]logAfter()...");
        System.out.println("[x]hijacked: " + joinPoint.getSignature().getName());
        System.out.println("[x]************");
    }
}
