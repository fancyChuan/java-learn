package base.oop;

public class SomeCases {
    public static void main(String[] args) {
        // testReferenceInstance();
        // testMultiParams(5, "天", "地", "人", "神", "鬼");
        // testPerson();
        // testOverwrite();
        // testHide();
        // testInitBlock();
        // testWrapper();
        testFinal();
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

    /**
     * 包装类示例：
     *      Integer.valueOf()       基本类型 -> 包装类型
     *      Integer.parseInt()      包装类型 -> 基本类型
     *
     *  自动装箱会出现一种特殊情况：
     *      -128~127    自动装箱成Integer，并放进cache的数组缓存起来。也就是说 下面的a/b是同一个对象
     *      不在这个范围内的，Integer就会新建一个新的实例，也就是说 c/d不是同个对象
     */
    public static void testWrapper() {
        System.out.println(String.valueOf(2.345f));
        System.out.println(String.valueOf(3.344));
        System.out.println(String.valueOf(true).toUpperCase());
        System.out.println("---------");
        System.out.println(Integer.valueOf("22") instanceof Integer); // true
        // System.out.println(Integer.parseInt("22") instanceof Integer); // 会报错 int 没有instanceof方法
        System.out.println(new Integer(2) == new Integer(2)); // false 包装类指向的不是同一个对象
        System.out.println(new Integer(2).equals(new Integer(2))); // true
        int i = 3;
        System.out.println(new Integer(i) == new Integer(i)); // 也是false，说明其实Integer实例化后得到的是一个新的对象
        System.out.println("---------");
        Integer a = 4;
        Integer b = 4;
        System.out.println(a == b); // true
        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d); // false
        System.out.println("---------");
        System.out.println(Integer.parseUnsignedInt("A", 16));
        System.out.println(Integer.toUnsignedString(-12, 16));
    }

    /**
     * System.out.println(a);  相当于 System.out.println(7);  也就是说，a是宏变量，或者说a就是直接量，编译器在编译时会直接替换a
     *
     * str2 在编译时无法确定下来，不是宏变量
     */
    public static void testFinal() {
        final int a = 5 + 2;
        final String str = "疯狂" + 99;
        final String str2 = "疯狂" + String.valueOf(99);
        System.out.println(str == "疯狂99"); // true   在完成str初始化前，JVM常量池会先缓存一个字符序列，这里比较的时候，都在常量池里的同一个字符序列
        System.out.println(str2 == "疯狂99"); // false

        System.out.println("------------");
        String s1 = "AABB";
        String s2 = "AA" + "BB";
        String A = "AA";
        String B = "BB";
        String s3 = A + B; // 编译时不能确定
        System.out.println(s1 == s2); // true
        System.out.println(s1 == s3); // false
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

/**
 * 重写equals()方法，只要TestEquals类的实例的IdStr相等就认为两对象相同
 */
class TestEquals {
    private String name;
    private String idStr;

    public TestEquals() {
    }

    public TestEquals(String name, String idStr) {
        this.name = name;
        this.idStr = idStr;
    }

    public String getIdStr() {
        return idStr;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj != null && obj.getClass() == TestEquals.class) {
            TestEquals test = (TestEquals) obj;
            if (this.getIdStr().equals(test.getIdStr())) {
                return true;
            }
        }
        return false;
    }
}