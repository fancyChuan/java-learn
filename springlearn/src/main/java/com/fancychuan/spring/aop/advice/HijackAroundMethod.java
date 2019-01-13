package com.fancychuan.spring.aop.advice;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class HijackAroundMethod implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("[XX]函数名：" + methodInvocation.getMethod().getName());
        System.out.println("[XX]函数参数： " + methodInvocation.getArguments().toString());

        System.out.println("[XX]before method");
        try {
            Object result = methodInvocation.proceed(); // 这是原方法调用执行的位置，在之前、之后都可以加代码

            System.out.println("[XX]after method"); // 相当于 AfterReturningAdvice 应该理解为原函数执行完，但是返回的结果还没有给用户

            return result;
        } catch (IllegalArgumentException e) {
            System.out.println("[XX]throw exception");
            throw e;
        }
    }
}