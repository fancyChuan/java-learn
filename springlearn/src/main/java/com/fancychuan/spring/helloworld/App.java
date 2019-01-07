package com.fancychuan.spring.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("SpringBeans.xml");

        HelloWorld obj = (HelloWorld) context.getBean("helloBean"); // getBean得到的是Object，需要显式转为HelloWorld
        obj.printHello();
    }
}
