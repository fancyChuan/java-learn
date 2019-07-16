package reflect;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws Exception {
        // testBootstrap();
        // testClassLoader();
        // testSelfClassLoader();
        // testCreateClazzInstance();
        testInvokeMethod();
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
}
