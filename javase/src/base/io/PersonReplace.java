package base.io;

import java.io.*;
import java.util.ArrayList;

/**
 * 自定义对象序列化 实现： writeReplace()
 */
public class PersonReplace implements Serializable {
    private String name;
    private int age;

    public PersonReplace(String name, int age) {
        System.out.println("有参构造器");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private Object writeReplace() throws ObjectStreamException {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(name);
        list.add(age);
        return list;
    }
}
