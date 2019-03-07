package base.oop.innerClass;

public class Main {
    public static void main(String[] args) {
        // testNotStaticInnerClass();
        // testSameVariable();
        testAccessInnerClass();
    }

    // 非静态内部类示例
    public static void testNotStaticInnerClass() {
        Cow cow = new Cow(66.66);
        cow.test();
    }

    // 有同样的变量名时，非静态内部类的取值特点
    public static void testSameVariable() {
        SameVariable same = new SameVariable();
        same.test();
    }

    // 外部类访问非静态内部类变量
    public static void testAccessInnerClass() {
        Outer outer = new Outer();
        outer.accessInnerProp(); // 这里只创建了外部类对象，此时accessInnerProp() 方法里必须要实例化非静态内部类，不然非静态内部类都不存在
    }
}
