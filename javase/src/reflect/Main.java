package reflect;

import reflect.demo.SomeOne;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws Exception {
        // testBootstrap();
        // testClassLoader();
        // testSelfClassLoader();
        // testCreateClazzInstance();
        // testInvokeMethod();
        // testField();
        // testArray();
        testGetClassInfo();
    }

    /**
     * 1. 根类加载器所价值的核心类库
     */
    public static void testBootstrap() {
        // 获取根类加载器所加载的类库所在的URL
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
        /*
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/resources.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/rt.jar                 String/System等核心类库都在rt.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/sunrsasign.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/jsse.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/jce.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/charsets.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/jfr.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/classes
        */
    }

    /**
     * 2. 访问类加载器
     *   * 类加载器的加载路径就是程序运行的当前路径
     *   * 扩展类加载器的parent是null，并不是根类加载器(根类加载器不继承ClassLooader抽象类)
     *   * 系统类加载器是AppClassLoader的实例，扩展类加载器是ExtClassLoader的实例。两个类都是URLClassLoader类的实例
     */
    public static void testClassLoader() throws IOException {
        // 系统类加载器
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器： " + systemLoader);
        Enumeration<URL> em1 = systemLoader.getResources("");
        while (em1.hasMoreElements()) {
            System.out.println(em1.nextElement());
        }
        // 拓展类加载器
        ClassLoader extensionLoader = systemLoader.getParent();
        System.out.println("拓展类加载器： " + extensionLoader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器的parent： " + extensionLoader.getParent());
        /* 运行结果
        =====================================
        系统类加载器： sun.misc.Launcher$AppClassLoader@14dad5dc
        file:/E:/javaProject/java-learn/javase/target/production/javase/
                拓展类加载器： sun.misc.Launcher$ExtClassLoader@74a14482
        扩展类加载器的加载路径：D:\ProgramData\jdk1.8.0_66\jre\lib\ext;C:\Windows\Sun\Java\lib\ext
        扩展类加载器的parent： null
        =====================================
        */
    }

    /**
     * 3. 测试自定义的类加载器。
     *  TODO：其实很疑惑，为什么在idea里面，总是会找不到路径呢？是相对路径的问题还是idea用了Java-agent的原因？
     */
    public static void testSelfClassLoader() throws Exception {
//        CompileClassLoader cll = new CompileClassLoader();
//        cll.compile("reflect\\TestClassLoader.java");

        String execClass = "reflect.TestClassLoader";
        String[] args = {"hello", "world", "hhh"};
        Object objs[] = {args};
        Object[] objs2 = args;
        CompileClassLoader ccl = new CompileClassLoader();
        Class<?> clazz = ccl.loadClass(execClass);
        Method main = clazz.getMethod("main", (new String[0]).getClass());
        main.invoke(null, objs);
    }

    /**
     * 5. 利用反射创建对象
     *  1. 从配置文件中读取类的名字并创建类的实例：使用 Class对象的newInstance()方法，也就是  Class.forName(name).newInstance()
     *  2. 使用指定构造器创建对象
     */
    public static void testCreateClazzInstance() throws Exception {
        ObjectPoolFactory factory = new ObjectPoolFactory();
        factory.initPool("E:\\JavaWorkshop\\java-learn\\javase\\src\\reflect\\obj-config.txt");
        // 配置文件中 a=java.util.Date 表示a是一个Date对象，那么经过对象池工厂处理后，我们就可以通过a这个关键词拿到一个Date对象
        System.out.println(factory.getObject("a"));
        System.out.println("自己创建的Date实例: " + new Date());

        // 下面演示通过指定的构造器创建对象
        Class<?> clazz = Class.forName("reflect.Person");
        Constructor<?> selfConstructor = clazz.getConstructor(String.class, int.class);
        Constructor<?> defaultConstructor = clazz.getConstructor();
        Object self = selfConstructor.newInstance("fancy", 233);
        Object def = defaultConstructor.newInstance();
        System.out.println(self);
        System.out.println(def);
    }

    /**
     * 6. 调用方法： Method.invoke(obj, params)
     *      Method为通过反射拿到的方法的信息
     *      obj是调用这个方法的实例对象，比如 person.setName("fancy")  那么person就是这里的obj
     */
    public static void testInvokeMethod() throws Exception {
        ExtendedObjectPoolFactory factory = new ExtendedObjectPoolFactory();
        factory.init("E:\\JavaWorkshop\\java-learn\\javase\\src\\reflect\\obj-config2.txt");
        factory.initPool();
        System.out.println("设置属性值之前：" + factory.getObject("person"));
        // 开始从配置文件中注入属性值
        factory.initProperty();
        System.out.println("设置属性值之后：" + factory.getObject("person"));
    }

    /**
     * 7. 访问成员变量值，适用于类没有提供getter/setter方法的时候
     *  注意，如果Person类没有setter方法，也是可以通过反射的方式给person对象的成员变量赋值的！
     */
    public static void testField() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        Class<Person> clazz = Person.class;
        Field nameField = clazz.getDeclaredField("name"); // 获取本类的成员变量
        Field ageField = clazz.getDeclaredField("age");
        nameField.setAccessible(true); // 取消访问权限检查
        ageField.setAccessible(true);
        // 给person对象赋值，成员变量的赋值需要在对象实例化之后才进行
        nameField.set(person, "xxx");
        ageField.setInt(person, 66);

        System.out.println(person);
    }

    /**
     * 8. 操作数组
     * TODO:有什么使用场景？
     */
    public static void testArray() {
        Object arr = Array.newInstance(String.class, 10);// 创建一个元素类型为String长度为10的数组
        Array.set(arr, 1, "这是第2个元素");
        Array.set(arr, 3, "这是第4个元素");

        String[] realArr = (String[]) arr;
        for (int i = 0; i < realArr.length; i++) {
            System.out.print(realArr[i] + "\t");
        }
        System.out.println();
        System.out.println(Array.get(arr, 1));
        System.out.println(Array.get(arr, 3));
        // 创建一个三维数组
        Object arr2 = Array.newInstance(String.class, 3, 4, 5);
        Object index2 = Array.get(arr2, 2); // 拿到的index2是三维数组的第3个元素，其本身是一个二维数组
        Array.set(index2, 1, new String[] {"三维0", "三维1", "三维2", "三维3", "三维4"}); // 再给index2这个二维数组的第2个元素赋值，值应该为一维数组

        String[][][] realArr2 = (String[][][]) arr2;
        System.out.println(realArr2[2][1][0]);
        System.out.println(realArr2[2][1][1]);
        System.out.println(realArr2[2][1][2]);
        System.out.println(realArr2[2][1][3]);
        System.out.println(realArr2[2][1][4]);
    }

    /**
     * 9. 获取类的信息
     */
    public static void testGetClassInfo() {
        Class<SomeOne> clazz = SomeOne.class;
        System.out.println("=========获得包名称=======");
        Package pkg = clazz.getPackage();
        System.out.println(pkg.getName());
        System.out.println("=========获得继承父类=======");
        Class<? super SomeOne> parent = clazz.getSuperclass();
        System.out.println(parent.getName());
        System.out.println(parent.getSuperclass().getName());
        System.out.println("=========获得父接口=======");
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }
        System.out.println("=========获得构造方法=======");
        System.out.println("=========获取普通方法=======");
        // Method中有方法的标识符、方法名、参数、返回值类型等，都可以通过反射拿到
        System.out.println("=========获取普通方法=======");
        System.out.println("=========获取成员变量=======");
        System.out.println("[父类]");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("[本类]");
        for (Field field: clazz.getDeclaredFields()) {
            System.out.println(field);
        }
        // Field中最常用的是getType()方法，常跟Method配合完成对象的getter/setter
        System.out.println("[父类成员类型]");
        for (Field field : fields) {
            System.out.println(field.getType().getName() + "\t" + field.getType().getSimpleName());
        }
    }

    /**
     * 10、Unsafe类的使用
     */
    public static void testUnsafe() throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafeObject = (Unsafe) field.get(null); // static属性不需要传递实例化对象
        // 利用Unsafe类绕过JVM管理机制，可以在没有实例化对象的情况下获取一个实例化对象
        SingletonNotMethod instance = (SingletonNotMethod) unsafeObject.allocateInstance(SingletonNotMethod);
        instance.print();
    }
}

/**
 * 这个类并没有对提供实例化对象的方法，这种情况就可以用Unsafe类绕过JVM的对象管理机制
 */
class SingletonNotMethod {
    private SingletonNotMethod() {
        System.out.println("私有的构造器");
    }
    public void print() {
        System.out.println("666");
    }
}
