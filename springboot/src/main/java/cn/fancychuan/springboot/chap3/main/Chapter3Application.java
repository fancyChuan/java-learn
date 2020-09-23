package cn.fancychuan.springboot.chap3.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = "cn.fancychuan.springboot.chap3")
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
public class Chapter3Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter3Application.class, args);
    }
}
