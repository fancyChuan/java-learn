package cn.fancychuan.spring.di.pojo;

import cn.fancychuan.spring.di.pojo.definition.Animal;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 优先级高，在使用Autowired装配的时候，优先注入
public class Cat implements Animal {
    @Override
    public void use() {
        System.out.println("这是猫【" + Cat.class.getSimpleName() + "】能抓老鼠");
    }
}
