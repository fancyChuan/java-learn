package cn.fancychuan.spring.di.pojo;

import cn.fancychuan.spring.di.pojo.definition.Animal;
import cn.fancychuan.spring.di.pojo.definition.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 带有参数的构造方法类的装配
 */
@Component
public class BussinessPersonWithPara implements Person {
    /** 取消了@Autowired对属性和方法的标注*/
    private Animal animal = null;

    public BussinessPersonWithPara(@Autowired @Qualifier("cat") Animal animal) {
        this.animal = animal;
    }

    @Override
    public void service() {
        this.animal.use();
    }

    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
