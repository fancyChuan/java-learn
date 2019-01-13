package com.fancychuan.spring;

import com.fancychuan.spring.aop.advice.StudentService;
import com.fancychuan.spring.aop.aspectj.ICustomerBo;
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
 * -----------
 * 20190109
 *      【bean自动扫描】 spring_auto
 *          有4种注释类型：1. @Component   表示一个自动扫描component
 *                      2. @Repository  表示一个持久化层的DAO component
 *                      3. @Service     表示一个业务逻辑层的Service component
 *                      4. @Controller  表示表示层的Controller component
 *          注：@Repository、 @Service 、 @Controller 三种注释是为了加强代码的阅读性而创造的,实际可以都可以使用@Component
 *          [*] 自动扫描中的过滤组件  详见SpringAuto.xml
 *              有两种组件： include-filter、exclude-filter
 *              注意：使用了过滤组件以后，就不需要使用 @Component进行注释了
 *      【bean自动装配】 自动装配autowire：指将一个bean注入到其他bean的property中
 *          有5种模式：  1. no            默认不使用，通过 ref 手动指定
 *                     2. byName        一个bean的property的name与另一个bean的id相同，则把另一个bean装配到这个bean中
 *                     3. byType        根据property的数据类型自动装配，<property name="x" class="A"/> 那么另一个bean的class为A会被装配（只允许一一对应）
 *                     4. constructor   会寻找参数数据类型相同的bean自动装配
 *                     5. autodetect    发现默认的构造函数，用constructor模式，负责byType模式，常与 dependency-check 连用
 * -----------
 * 20190110
 *      【AOP-advice】 aop.advice
 *          面向切面编程，本质是一个拦截器(interceptor)，拦截要执行的方法，在之前或者之后加入代码、功能。使用了**代理模式**
 *          主要有4种：  类型                      需要实现的接口                 说明
 *              1. before advice            MethodBeforeAdvice          函数执行前拦截
 *              2. after return advice      AfterReturningAdvice        函数执行完返回值以后拦截，但还没有返给用户
 *              3. throw exception          ThrowsAdrice                抛出异常的时候拦截
 *              4. around advice            MethodInterceptor           综合三种方式
 * ------------
 * 20190113
 *      【AOP-pointcut&advisor】
 *          *advice     表示一个方法调用前后的动作，可以理解为一个事件、步骤。或者说就是向程序内部注入的代码
 *          *pointcut   切入点，根据方法的名字或者正则表达式决定是否拦截方法。或者说注入advice的位置
 *          *advisor    advice和pointcut组成的独立单元，可以传给proxy factory对象
 *
 *      【AOP-auto proxy】 AOP-AutoProxy.xml
 *          * 用org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator 建立proxy生成规则
 *          * 用org.springframework.aop.support.NameMatchMethodPointcutAdvisor定义拦截规则
 *
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

    private static void testAopAdvice() {
        context = new ClassPathXmlApplicationContext("AOP-Advice.xml");
        StudentService cust = (StudentService) context.getBean("studentServiceProxy");
        System.out.println("使用Spring AOP 如下");
        System.out.println("*************************");
        cust.printName();
        System.out.println("*************************");
        cust.printUrl();
        System.out.println("*************************");

        try {
            cust.printThrowException();
        } catch (Exception e) {

        }
    }

    private static void testAopProxy() {
        context = new ClassPathXmlApplicationContext("AOP-AutoProxy.xml");
        StudentService cust = (StudentService) context.getBean("studentService");
        System.out.println("使用Spring AOP 如下");
        System.out.println("*************************");
        cust.printName();
        System.out.println("*************************");
        cust.printUrl();
        System.out.println("*************************");

        try {
            cust.printThrowException();
        } catch (Exception e) {

        }
    }

    private static void testAspectJ() {
        context = new ClassPathXmlApplicationContext("AOP-AspectJ.xml");
        ICustomerBo customer = (ICustomerBo) context.getBean("customerBo");
        customer.addCustomer();
        System.out.println("-----------");
        customer.deleteCustomer();
    }

    public static void main(String[] args) {
        // testInnerBean();
        // testServices();
        // testCollections();
        // testSpringAuto();
        // testAopAdvice();
        // testAopProxy();
        testAspectJ();
    }
}
