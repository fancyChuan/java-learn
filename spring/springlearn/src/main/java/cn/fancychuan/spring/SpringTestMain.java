package cn.fancychuan.spring;

import cn.fancychuan.spring.di.pojo.AppConfigUseScan;
import cn.fancychuan.spring.di.pojo.BussinessPerson;
import cn.fancychuan.spring.di.pojo.definition.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTestMain {

    @Test
    public void testAutowire() {
        // 注意这里要使用AppConfigUseScan这个配置类，而这个配置类设置了扫描，能够找到BussinessPerson
        // 如果这里使用AppConfig，以为AppConfig并没有设置配置BussinessPerson，就会导致找不到bean
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigUseScan.class);
        Person person = ctx.getBean(BussinessPerson.class);
        person.service();
    }

}
