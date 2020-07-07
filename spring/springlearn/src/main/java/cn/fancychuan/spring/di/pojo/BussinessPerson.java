package cn.fancychuan.spring.di.pojo;

import cn.fancychuan.spring.di.pojo.definition.Animal;
import cn.fancychuan.spring.di.pojo.definition.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Autowire的几种注入机制
 *  1. 根据类型
 *      @Autowired
 *      private Animal animal = null;
 *      这种情况，适合于Animal只有一个类，或者说只有一个子类，
 *      当有多个子类时，就会因为不知道应该装配哪个bean而报错。比如有Cat和Dog两个类的时候
 *  2. 根据属性名和Bean的名称进行匹配
 *      @Autowired
 *      private Animal dog = null; // 实例名改成想要用的名字dog，那么装配的时候就会使用Dog
 *      还有种方法就是使用@Primary和Quelifier注解用来消除歧义
 */
@Component
public class BussinessPerson implements Person {
    @Autowired
    @Qualifier(value = "dog") // 使用了Qualifier配合Autowired就可以通过类型和名称一起去找到Bean
    private Animal animal = null;
    // 上面这一行说明，人类依赖于动物，就存在DI依赖注入的问题

    @Override
    public void service() {
        this.animal.use();
    }

    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
