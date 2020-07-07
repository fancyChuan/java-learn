package cn.fancychuan.spring.di.pojo;

import cn.fancychuan.spring.di.pojo.definition.Animal;
import cn.fancychuan.spring.di.pojo.definition.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BussinessPerson implements Person {
    @Autowired
    private Animal animal = null;

    @Override
    public void service() {
        this.animal.use();
    }

    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
