package com.fancychuan.spring.loosely_coupled;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 20190107 用 Spring 依赖注入调用输出
 *
 * 体现了：Spring松耦合特点
 *
 */
public class App {
    private static ApplicationContext context;

    public static void main(String[] args){
        context = new ClassPathXmlApplicationContext("Spring-Output.xml");
        OutputHelper obj = (OutputHelper) context.getBean("OutputHelper");
        obj.generateOutput();
    }
}
