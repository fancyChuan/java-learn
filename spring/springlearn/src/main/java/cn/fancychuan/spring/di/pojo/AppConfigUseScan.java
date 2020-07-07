package cn.fancychuan.spring.di.pojo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 使用ComponentScan注解的配置类
 * AnnotationConfigApplicationContext使用到这个类的时候，就可以找到由ComponentScan扫描到的bean
 */
@Configuration
@ComponentScan
public class AppConfigUseScan {
}
