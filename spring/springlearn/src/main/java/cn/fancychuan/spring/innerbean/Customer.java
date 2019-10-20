package cn.fancychuan.spring.innerbean;

public class Customer {
    private Person person;

    public Customer(Person person) { // 带参构造方法
        this.person = person;
    }

    public Customer() {} //有带参构造方法一定要有默认的构造方法，否则报 No default constructor found 的错误
    // 在InnerBean.xml配置文件中，当使用第三种注入方式，也就是 构造函数 注入的时候，可以不需要加这个默认的构造函数
    // TODO: 内部的调用顺序过程是什么？

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Customer [person=" + person + "]";
    }
}
