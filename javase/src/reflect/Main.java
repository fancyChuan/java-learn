package reflect;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws Exception {
        // testBootstrap();
        // testClassLoader();
        // testSelfClassLoader();
        CompileClassLoader cll = new CompileClassLoader();
        cll.compile("reflect/TestClassLoader.java");
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

    public static void testSelfClassLoader() throws Exception {
        String execClass = "reflect.TestClassLoader";
        String[] args = {"hello", "world", "hhh"};
        Object objs[] = {args};

        CompileClassLoader ccl = new CompileClassLoader();
        Class<?> clazz = ccl.loadClass(execClass);
        Method main = clazz.getMethod("main", (new String[0]).getClass());
        main.invoke(null, objs);
    }
}
