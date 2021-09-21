package cn.fancychuan.springboot;

import cn.fancychuan.springboot.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. 配置类中使用@Bean注解在方法上给容器注册组件，默认也是单实例的
 * 2. 配置类本身也是组件
 * 3. proxy
 */
@Configuration
public class MyConfig {

    /**
     *
     * @return
     */
    @Bean
    public Dog dog01() {
        return new Dog("dog1", 2);
    }
}

