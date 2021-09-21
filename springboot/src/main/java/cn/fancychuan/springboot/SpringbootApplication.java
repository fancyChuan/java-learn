package cn.fancychuan.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @SpringBootApplication 用来表示一个主程序类，说明这是一个SpringBoot应用
 * 也是一个主配置类
 */
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		// 1. 返回IOC容器
		ConfigurableApplicationContext run = SpringApplication.run(SpringbootApplication.class, args);
		// 2. 查看容器中的组件
		String[] names = run.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}

}
