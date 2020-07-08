package cn.fancychuan.spring.di.life;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 使用ComponentScan注解的配置类
 * AnnotationConfigApplicationContext使用到这个类的时候，就可以找到由ComponentScan扫描到的bean
 */
@Configuration
// 注意这里要写好basePackage 不写的话，BussinessPersonLifeCycle会找不到dog这个bean
@ComponentScan(basePackages = "cn.fancychuan.spring.di")
public class AppConfigForLife {
}
