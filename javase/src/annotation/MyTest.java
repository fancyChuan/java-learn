package annotation;

/**
 * 定义了一个类，并指明有哪些方法是需要测试的
 */
public class MyTest {
    @Testable
    public static void m1() {}

    public static void m2() {}

    @Testable
    public static void m3() {
        throw new IllegalArgumentException("参数出错啦!");
    }

    @Testable
    public static void m4() {
        throw new RuntimeException("程序运行出错啦");
    }

}
