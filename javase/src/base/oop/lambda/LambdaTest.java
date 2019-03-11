package base.oop.lambda;

import base.oop.interfacePackage.Command;
import base.oop.interfacePackage.ProcessArray;

import javax.swing.*;

/**
 * 1. 匿名内部类与Lambda的用法区别                    testLambdaAnonymous()
 * 2. 测试Lambda表达式的各种简写                      testLambdaUsage()
 * 3. Lambda方法引用、构造器引用示例                   testUsing()
 * 4. Lambda表达式和匿名内部类的相同点                  testLambdaInnerSame()
 */
public class LambdaTest {

    public static void main(String[] args) {
        // testLambdaAnonymous();
        // testLambdaUsage();
        // testUsing();
        testLambdaInnerSame();
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

    /**
     * 测试Lambda表达式的方法引用，构造器引用
     */
    public static void testUsing() {
        Converter converter1 = from -> Integer.valueOf(from);
        Integer val1 = converter1.convert("99");
        System.out.println(val1);
        // 第一种方法引用，引用类方法(Integer.valueOf)，函数式接口中被实现方法的全部参数传给该类方法作为参数
        Converter converter2 = Integer::valueOf;
        Integer val2 = converter1.convert("88");
        System.out.println(val2);
        // 第二种方法引用：引用特定对象的实例方法
        // Converter converter3 = from -> "fkit.org".indexOf(from); // 特定对象 "fkit.org" 实例对象的方法
        Converter converter3 = "fkit.org"::indexOf;
        Integer val3 = converter3.convert("it");
        System.out.println(val3);
        // 第三种方法引用：引用某类对象的实例方法，函数式接口中被实现方法的第一个参数作为调用者，后面的参数全部传给该方法作为参数
        // MyTest myTest = (a, b, c) -> a.substring(b, c); // 某类对象在这里是所有String对象
        MyTest myTest = String::substring;
        String str = myTest.test("what? are you crazy?", 6, 20);
        System.out.println(str);
        // 引用构造器：函数式接口中被实现方法的全部参数传给该构造器作为参数
        // YourTest yourTest = (String a) -> new JFrame(a); // 基本的写法，显式指定参数的类型为string，这样就会使用带一个String参数的构造器
        YourTest yourTest = JFrame::new;
        JFrame jf = yourTest.win("你的窗口");
        System.out.println(jf);
    }

    public static void testLambdaInnerSame() {
        LambdaAndInner lambda = new LambdaAndInner();
        lambda.test();
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

@FunctionalInterface
interface Converter {
    Integer convert(String from);
}
@FunctionalInterface
interface MyTest {
    String test(String a, int b, int c);
}
@FunctionalInterface
interface YourTest {
    JFrame win(String title);
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