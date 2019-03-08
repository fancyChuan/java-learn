package base.oop.lambda;

import base.oop.interfacePackage.Command;
import base.oop.interfacePackage.ProcessArray;

public class LambdaTest {

    public static void main(String[] args) {
        // testLambdaAnonymous();
        testLambdaUsage();
    }

    public static void testLambdaAnonymous() {
        ProcessArray p = new ProcessArray();
        int[] array = {1, 3, 5, 8};
        p.process(array, new Command() {
            @Override
            public void process(int[] array) {
                int sum = 0;
                for (int tmp: array) {
                    sum += tmp;
                }
                System.out.println("使用匿名内部类实现的命令，结果为：" + sum);
            }
        });

        p.process(array, (int[] target) -> {
            for (int tmp: target) {
                System.out.println("使用Lambda表达式实现的命令，元素有： " + tmp);
            }
        });
    }

    /**
     * 测试Lambda表达式的各种简写
     */
    public static void testLambdaUsage() {
        LambdaQs lq = new LambdaQs();
        // 代码块只有一条语句，可以省略花括号
        lq.eat(() -> System.out.println("苹果的味道不错"));
        // 只有一个形参，可以省略圆括号
        lq.drive(weather -> {
            System.out.println("今天天气：" + weather);
        });
        // 代码块中只有一条语句，即使需要返回值，也可以省略return关键词
        lq.test((a, b) -> a + b);
    }

    /**
     * 测试Lambda表达式的限制
     */
    public static void testFunction() {
        // 用Lambda表达式实现了Runable中惟一的无参方法，得到一个Runable对象r
        Runnable r = () -> {
            for (int i=0; i<10; i++) {
                System.out.println(i);
            }
        };
        // Lambda表达式的目标类型必须是明确的函数式接口，而Object不是明确的，所以下面的代码编译错误
        /*
        Object obj = () -> {
            for (int i=0; i<10; i++) {
                System.out.println(i);
            }
        };
        */
        // 强制类型转换成函数式接口后就可以了，而FKTest是一个用@FunctionalInterface修饰的函数式接口
        Object obj = (FKTest) () -> {
            for (int i=0; i<10; i++) {
                System.out.println(i);
            }
        };
    }
}


interface Eatable {
    void taste();
}
interface Flyable {
    void fly(String weather);
}
interface Addable {
    int add(int a, int b);
}
class LambdaQs {
    public void eat(Eatable e) {
        System.out.println(e);
        e.taste();
    }
    public void drive(Flyable f) {
        System.out.println("我正在驾驶: " + f);
        f.fly("飞啊啊啊啊啊啊");
    }
    public void test(Addable add) {
        System.out.println(add.add(5, 3));
    }
}