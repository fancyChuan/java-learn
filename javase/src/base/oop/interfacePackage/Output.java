package base.oop.interfacePackage;

public interface Output {
    // 接口中的变量只能是静态常量
    int MAX_LINE = 50;

    // 接口中的普通方法只能是public的抽象方法，系统会默认加上 public abstract
    void out();
    void getData(String msg);

    // 接口中定义默认方法，要加default修饰符，默认会加上public
    default void print(String... msgs) {
        for (String msg: msgs) {
            System.out.println(msg);
        }
    }
    default void test() {
        System.out.println("默认的test()方法");
    }

    // 类方法，要用static修饰
    static String staticTest() {
        return "接口中的方法";
    }
}
