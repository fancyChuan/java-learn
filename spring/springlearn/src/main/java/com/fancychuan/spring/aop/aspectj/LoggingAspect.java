package com.fancychuan.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect // 加上这个注释以后才方便被框架扫描到
public class LoggingAspect {

    // execution是切入点表达式
    @Before("execution(public * com.fancychuan.spring.aop.aspectj.CustomerBo.addCustomer(..))")
    // * 表示返回值类型
    // .. 表示参数匹配，此处表示匹配任意数量的参数。 使用 (* , String)，代表匹配两个参数，第二个参数是String类型的参数，第一个任意类型
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

    @AfterReturning("com.fancychuan.spring.aop.aspectj.PointcutsDefinition.custmerLog()")
    // execution表达式已经在PointcutsDefinition，这里只需要使用便捷的别名 custmerLog()
    public void logAfterReturn(JoinPoint joinPoint) {
        System.out.println("[x]logAfterReturn()...");
        System.out.println("[x]hijacked: " + joinPoint.getSignature().getName());
        System.out.println("[x]************");
    }
}
