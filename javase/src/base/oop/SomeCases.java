package base.oop;

public class SomeCases {
    public static void main(String[] args) {
        // testReferenceInstance();
        // testMultiParams(5, "天", "地", "人", "神", "鬼");
        // testPerson();
        // testOverwrite();
        // testHide();
        testInitBlock();
    }

    /**
     * 20190225 演示java值传递机制
     *
     * 这个示例容易造成误解，认为传到swap()方法中的是dx对象本身，而不是复制品
     */
    public static void testReferenceInstance() {
        DataX dx = new DataX(); // dx实际上存的是对象的引用，也就是对象的地址，对象的数据其实放在堆内存中
        dx.a = 1;
        dx.b = 2;

        swap(dx); // 传进来的照样是复制品，只不过是 dx对象的引用地址，所以，在方法中对对象的操作通过引用，实际影响了对象本身。
        System.out.println("a: " + dx.a + "\tb: " + dx.b);
    }
    public static void swap(DataX dx) {
        int tmp = dx.a;
        dx.a = dx.b;
        dx.b = tmp;
    }

    /**
     * 形参的个数可变
     */
    public static void testMultiParams(int a, String... books) {
        for (String tmp: books) {
            System.out.println(tmp);
        }
        System.out.println(a);
    }

    /**
     * 通过实例去修改静态变量（类变量），因为静态变量不属于实例，所以实际上还是修改的类的类变量，也就是类的静态变量会直接受影响
     *
     * 另外，虽然不会报错，但是java允许实例去访问静态变量这个设计本身就有缺陷
     */
    public static void testPerson() {
        System.out.println(Person.eyeNum); // 0  静态变量被默认初始化为0，数组的默认初始化也是每个元素为0
        Person person = new Person();
        System.out.println(person.name + " with " + person.eyeNum); // null with 0
        person.name = "我的天";
        person.eyeNum = 2; // 对静态变量赋值，在同个JVM里面，该静态变量就被影响了
        System.out.println(person.name + " with " + person.eyeNum); // 我的天 with 2
        System.out.println(Person.eyeNum); // 2
    }

    /**
     * 方法的局部变量可以与类的成员同名
     */
    public static void testOverwrite() {
        int price = 66;
        System.out.println(price);
        System.out.println(Overwrite.price);
        new Overwrite().info();
    }

    /**
     * 子类和父类同名的变量并不会覆盖，只是简单的隐藏起来了。系统会用两块内存分别存储
     */
    public static void testHide() {
        Derived derived = new Derived();

        // System.out.println(derived.tag); // 这一行在编译就会报错

        System.out.println(((Parent) derived).tag); // 向上转型后就可以访问
    }


    public static void testInitBlock() {
        InitBlock initBlock = new InitBlock();
        System.out.println("---------");
        InitBlock initBlock2 = new InitBlock();
    }
}


class DataX {
    int a;
    int b;
}


class Person {
    public String  name;
    public static int eyeNum;
}

class Overwrite {
    protected String name = "张三";
    protected static double price = 78.0;

    public void info() {
        String name = "李四";
        System.out.println(name);
        System.out.println(this.name);
    }
}

class MultiConstruct {
    public String name;
    public String color;
    public double weight;

    public MultiConstruct(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // 三个参数的构造器，代码体完全包含了两个参数的构造器
    public MultiConstruct(String name, String color, double weight) {
        this(name, color); // 通过this调用另一个构造器
        this.weight = weight;
    }
}

/**
 * 演示子类变量隐藏父类的示例
 */
class Parent {
    public String tag = "这是父类";
}
class Derived extends Parent {
    private String tag = "子类中的"; // 这个私有的变量会隐藏父类的
}

class InitBlock {

    {
        int a = 6;
        System.out.println("第一个初始化块，定义了a=" + a);
    }

    static {
        // if (this.a > 4) {} // 这个时候构造器还没执行，this无法使用，同时也无法直接使用变量a
        System.out.println("第2个是静态初始化块..");
    }

    public InitBlock() {
        System.out.println("执行构造器");
    }
}