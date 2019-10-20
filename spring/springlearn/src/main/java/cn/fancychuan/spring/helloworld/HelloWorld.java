package cn.fancychuan.spring.helloworld;

public class HelloWorld {
    private String name;
    private int age;

    public void setName(String n){
        this.name=n;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void printHello(){
        System.out.println("The first Spring : hello" + name);
        System.out.println("The first Spring : you age is " + age);
    }
}
