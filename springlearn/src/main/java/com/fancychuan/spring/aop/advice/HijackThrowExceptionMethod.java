package com.fancychuan.spring.aop.advice;

import org.springframework.aop.ThrowsAdvice;

public class HijackThrowExceptionMethod implements ThrowsAdvice {

    // TODO：很奇怪，这个afterThrowing方法并没有在接口中定义，根本就没有重写，而且函数改个名就不能运行，为什么？
    public void afterThrowing(IllegalArgumentException e) throws Throwable {
        System.out.println("[***]HijackThrowException : Throw exception hijacked!");
    }

}
