package baselib;

import java.util.Objects;

public class ObjectTest {
    static ObjectTest obj;

    public static void main(String[] args) {
        // testClone();
        testObjects();
    }

    /**
     * 测试clone()方法，运行结果： false true
     *
     * 说明这个复制只是对实例变量的复制，对于引用类型，也只是简单复制，沿用这个应用变量
     */
    public static void testClone() {
        User u1 = new User(25);
        try {
            User u2 = u1.clone();
            System.out.println(u1 == u2);
            System.out.println(u1.address == u2.address);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试Objects工具类
     */
    public static void testObjects() {
        System.out.println(Objects.hashCode(obj)); // null对象，返回0
        System.out.println(Objects.toString(obj)); // 返回"null" 如果在Object就空指针异常了
        System.out.println(Objects.requireNonNull(obj, "obj参数不能为null的呐")); // 主要用来对形参做输入校验
    }
}



class Address {
    String detail;

    public Address(String detail) {
        this.detail = detail;
    }
}

class User implements Cloneable {
    int age;
    Address address;

    public User(int age) {
        this.age = age;
        this.address = new Address("深圳啦");
    }

    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}