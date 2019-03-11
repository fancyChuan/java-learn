package base.oop.lambda;

@FunctionalInterface
interface Displayable {
    void display();
    default int add(int a, int b) {
        return a + b;
    }
}

/**
 * 演示Lambda表达式和匿名内部类的相同点
 */
public class LambdaAndInner {
    private int age = 24;
    private static String name = "疯狂java";
    public void test() {
        String book = "讲义啊";
        Displayable dis = () -> {
            // 访问effectively final变量，其实因为Lambda表达式访问了book局部变量，所以book就相当于有一个隐式的final修饰
            System.out.println("访问局部变量：" + book);
            System.out.println("外部类的age：" + age);
            System.out.println("外部类的name:" + name);
            // 不允许在Lambda的方法体中调用默认方法
            // System.out.println(add(3, 4));
        };
        dis.display();
        // dis对象可以调用继承的默认方法add()
        System.out.println(dis.add(3, 4));
    }
}
