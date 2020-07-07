package cn.fancychuan.spring;

import cn.fancychuan.spring.di.pojo.AppConfigUseScan;
import cn.fancychuan.spring.di.pojo.BussinessPerson;
import cn.fancychuan.spring.di.pojo.BussinessPersonWithPara;
import cn.fancychuan.spring.di.pojo.definition.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTestMain {

    @Test
    public void testAutowired() {
        // 注意这里要使用AppConfigUseScan这个配置类，而这个配置类设置了扫描，能够找到BussinessPerson
        // 如果这里使用AppConfig，以为AppConfig并没有设置配置BussinessPerson，就会导致找不到bean
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigUseScan.class);
        Person person = ctx.getBean(BussinessPerson.class);
        person.service();
    }

    /**
     * 带有参数的构造方法类的装配
     */
    @Test
    public void testContructorWithPara() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigUseScan.class);
        BussinessPersonWithPara person = ctx.getBean(BussinessPersonWithPara.class);
        person.service();
    }

}
