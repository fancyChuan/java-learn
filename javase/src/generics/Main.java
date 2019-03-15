package generics;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // testExtends();
        // testNotNewClass();
        testLimitMatch();
    }

    /**
     * 1. 从泛型类派生子类
     */
    public static void testExtends() {
        A1 a1 = new A1("nide");
        A2 a2 = new A2("nide");
        a1.getInfo();
        a2.getInfo();
    }

    /**
     * 2. 系统并没有把泛型当成类来处理，也就是说不管泛型的实际类型参数是什么，在运行时总是同样的类
     */
    public static void testNotNewClass() {
        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        System.out.println(list1.getClass() == list2.getClass()); // 返回true
    }

    /**
     * 3. List<String> 不是List<Object>的子类，要特别注意
     */
    public static void testISNotSubClass() {
        List<String> list = new ArrayList<>();
        // test(list); // 这行编译报错
    }
    private static void test(List<Object> l) {
        for (int i=0; i<l.size(); i++) {
            System.out.println(l.get(i));
        }
    }

    /**
     * 4. 带上限的类型通配符，具体查看 Canvas类的实现
     */
    private static void testLimitMatch() {
        Canvas canvas = new Canvas();

        List list1 = new ArrayList();// 不指定类型，虽然有警告，但能运行
        list1.add(new Circle());
        list1.add(new Rectangle());
        canvas.drawAll2(list1); // 执行类型通配符带上限的方法

        List<Circle> list2 = new ArrayList<>();
        list2.add(new Circle());
        list2.add(new Circle());
        // list2.add(new Rectangle()); // 这一行编译就报错了，指定了元素的类型为 Circle
        canvas.drawAll2(list2);
    }
}




/**
 * 从Apple<String>派生子类，Apple中所有使用T类型的地方都需要替换成String，也就是子类会继承 String getInfo() 方法
 */
class A1 extends Apple<String> {
    public A1(String info) {
        super(info);
    }

    // 返回值类型必须是String
    public String getInfo() {
        return "子类" + super.getInfo();
    }
    /* 下面的代码就是错误的，重写父类方法时，返回值类型不一致
    public Object getInfo() {
        return "子类";
    }
    */
}

/**
 * 不传入实际类型参数，java编译器会有警告：使用了未经检查或不安全的操作。
 * javac -Xlint:unchecked 重新编译后可以看到更详细的信息
 */
class A2 extends Apple {
    // 这里系统会把T当成Object处理
    public A2(Object info) {
        super(info);
    }

    public String getInfo() {
        return super.getInfo().toString();
    }
}