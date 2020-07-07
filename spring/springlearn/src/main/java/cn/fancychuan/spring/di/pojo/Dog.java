package cn.fancychuan.spring.di.pojo;

import cn.fancychuan.spring.di.pojo.definition.Animal;
import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {
    @Override
    public void use() {
        System.out.println("这是狗【" + Dog.class.getSimpleName() + "】能够看门");
    }
}
