package generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // testExtends();
        // testNotNewClass();
        // testLimitMatch();
        testGenericMothed();
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
    public static void testLimitMatch() {
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

    /**
     * 5. 泛型方法示例
     */
    public static void testGenericMothed() {
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<>();
        GenericMethod.fromArrayToCollection(oa, co); // T代表Object

        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<>();
        GenericMethod.fromArrayToCollection(sa, cs); // T代表String

        // 上面的用法也表名，泛型方法在调用的时候，直接传入实参，不需要显式指明实参的类型，而系统回推断成T代表的类型
        // 下面的代码演示传入的实参不同时，系统会无法判断T应该是Object还是String
        List<String> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        // GenericMethod.test1(list1, list2); 这一行会报错，因为无法推断T的类型
        GenericMethod.test2(list1, list2); // 只要满足list1是list2类型的子类就可以

    }

    /**
     * 6. 泛型构造器
     */
    public static void testGenericConstructor() {
        new Foo("xxxx");
        new Foo(200);

        new <String> Foo("fancy");
        // new <String> Foo(11); // 这行代码会报错，因为显式指明了类型形参T的类型是String，但是实际传了Integer，会报错

        MyClass<String> mc0 = new MyClass<>(5); // 利用了Java7新增的“棱形”语法，可以不显式声明构造器类型
        MyClass<String> mc1 = new <Integer> MyClass<String>(5); // 类声明中的E形参是String，构造器声明的T是Integer
        // MyClass<String> mc2 = new <Integer> MyClass<>(5); // 显式指定了泛型构造器中类型形参的类型，就不能使用“菱形”语法了
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

/**
 * 泛型方法
 */
class GenericMethod {
    // 声明一个泛型方法，泛型方法中带一个T类型形参
    static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o: a) {
            c.add(o);
        }
    }

    // 下面语法上没问题，就只只能添加Object的元素，String的元素是无法添加到c中的
    // a.add("xx")没问题，而c.add("xx")就会报错 因为Collection<Object>不是Collection<String>的父类
    // static void fromArrayToCollection(Object[] a, Collection<Object> c) {}
    // 上面这个，把Object换成通配符?也不行，因为在方法中，泛型类不能确定，无法执行 c.add()


    // 容易出错，当from和to的类型不一样时，哪怕是存在父子类关系
    static <T> void test1(Collection<T> from, Collection<T> to) {
        for (T ele: from) {
            to.add(ele);
        }
    }

    // from 是 to 的子类型，也就是说，第二个参数to是第一个参数from的父类，系统就可以推断出T的类型与to的类型一样
    static <T> void test2(Collection<? extends T> from, Collection<T> to) {
        for (T ele: from) {
            to.add(ele);
        }
    }
}

/**
 * 泛型构造器
 */
class Foo {
    public <T> Foo(T t) { // <T> 在这里相当于就是先声明了形参类型T
        System.out.println(t);
    }
}
class MyClass<E> {
    public <T> MyClass(T t) {
        System.out.println("t的类型是：" + t);
    }
}