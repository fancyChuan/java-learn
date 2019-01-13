package com.fancychuan.spring.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 对于 PointcutsDefinition 来说，主要职责是定义 Pointcut ，可以在其中定义多个切入点，并且可以用便于记忆的方法签名进行定义
 */
@Aspect
public class PointcutsDefinition {

    // 切入点声明
    @Pointcut("execution(* com.fancychuan.spring.aop.aspectj.CustomerBo.addCustomer*(..))")
    public void custmerLog() {} // 是一个签名，在advice中可以用这个签名代替切入点表示式，只起到助记作用，所以不需要实际代码
}
