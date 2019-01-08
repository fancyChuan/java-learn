package com.fancychuan.spring;

import com.fancychuan.spring.helloworld.HelloWorld;
import com.fancychuan.spring.innerbean.Customer;
import com.fancychuan.spring.loosely_coupled.OutputHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 20190107 【用Spring依赖注入 调用输出】 loosely_coupled
 * 体现了：Spring松耦合特点
 *
 * -----------
 * 20190108 【bean内部嵌套bean】 innerbean
 * 一般在一个类接受另一个类的实例作为参数的时候，需要用到嵌套bean，当然也可以在bean中注入构造函数来达到同样的目的 详见InnerBean.xml
 *
 */
public class Main {
    private static ApplicationContext context;

    private static void testHelloWorld() {
        context = new ClassPathXmlApplicationContext("SpringBeans.xml");

        HelloWorld obj = (HelloWorld) context.getBean("helloBean"); // getBean得到的是Object，需要显式转为HelloWorld
        obj.printHello();
    }

    private static void testLooselyCoupled() {
        context = new ClassPathXmlApplicationContext("Spring-Output.xml");
        OutputHelper obj = (OutputHelper) context.getBean("OutputHelper");
        obj.generateOutput();
    }

    private static void testInnerBean() {
        context = new ClassPathXmlApplicationContext("InnerBean.xml");
        Customer customer = (Customer) context.getBean("CustomerBean");
        System.out.println(customer.toString());

        Customer customer2 = (Customer) context.getBean("CustomerBean2");
        System.out.println(customer2.toString());

        Customer customer3 = (Customer) context.getBean("CustomerBean3");
        System.out.println(customer3.toString());
    }

    public static void main(String[] args) {
        testInnerBean();
    }
}
