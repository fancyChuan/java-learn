package com.fancychuan.spring;

import com.fancychuan.spring.collections.Customers;
import com.fancychuan.spring.helloworld.HelloWorld;
import com.fancychuan.spring.innerbean.Customer;
import com.fancychuan.spring.innerbean.Person;
import com.fancychuan.spring.loosely_coupled.OutputHelper;
import com.fancychuan.spring.services.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 20190107 【用Spring依赖注入 调用输出】 loosely_coupled
 * 体现了：Spring松耦合特点
 *
 * -----------
 * 20190108
 *      【bean内部嵌套bean】 innerbean
 *          一般在一个类接受另一个类的实例作为参数的时候，需要用到嵌套bean，当然也可以在bean中注入构造函数来达到同样的目的 详见InnerBean.xml
 *      【bean作用域】 services
 *          spring 支持5种类型：
 *          singleton — 单例模式，由 IOC 容器返回一个唯一的 bean 实例。
 *          prototype — 原型模式，被请求时，每次返回一个新的 bean 实例。
 *          request — 每个 HTTP Request 请求返回一个唯一的 Bean 实例。
 *          session — 每个 HTTP Session 返回一个唯一的 Bean 实例。
 *          globalSession — Http Session 全局 Bean 实例。
 *
 *          [*]大多数情况下，只需要处理 Spring 的核心作用域 — 单例模式和原型模式，默认情况下，作用域是单例模式。
 *      【bean集合】 collections
 *          list/set/map/props标签 见 Collections.xml
 *      【bean注解】
 *
 * -----------
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

    private static void testServices() {
        context = new ClassPathXmlApplicationContext(new String[] {"SpringBeans.xml"});

        // singleton 多次获得bean，但是只有一个实例
        CustomerService singletonA = (CustomerService) context.getBean("services-singleton");
        singletonA.setMessage("[*]singleton message..");
        System.out.println(singletonA.getMessage());
        CustomerService singletonB = (CustomerService) context.getBean("services-singleton");
        System.out.println(singletonB.getMessage());

        // prototype 每次getbean都会新建一个实例
        CustomerService prototypeA = (CustomerService) context.getBean("services-prototype");
        prototypeA.setMessage("[x]prototype message..");
        System.out.println(prototypeA.getMessage());
        CustomerService prototypeB = (CustomerService) context.getBean("services-prototype");
        System.out.println(prototypeB.getMessage());
    }

    private static void testCollections() {
        context = new ClassPathXmlApplicationContext("Collections.xml");
        Customers customers = (Customers) context.getBean("customers");

        System.out.println(customers.getLists().toString());
        System.out.println(customers.getSets().toString());
        System.out.println(customers.getMaps().toString());
        System.out.println(customers.getProperties().toString());

        System.out.println(customers);

        Person person = (Person) context.getBean("personBean");
        System.out.println(person.toString());
    }

    private static void testSpringAuto() {
        context = new ClassPathXmlApplicationContext("SpringAuto.xml");
        com.fancychuan.spring.spring_auto.services.CustomerService cs = (com.fancychuan.spring.spring_auto.services.CustomerService) context.getBean("customerService");
        System.out.println(cs);
    }

    public static void main(String[] args) {
        // testInnerBean();
        // testServices();
        // testCollections();
        testSpringAuto();
    }
}
