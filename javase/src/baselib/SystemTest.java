package baselib;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class SystemTest {

    public static void main(String[] args) {
        // testSystemEnv();
        // testIdentityHashCode();
        // testRuntime();
        testExec();
    }

    public static void testSystemEnv() {
        // 所有环境变量
        Map<String, String> env = System.getenv();
        for (String name: env.keySet()) {
            System.out.println(name + " -> " + env.get(name));
        }
        // 获取特定环境变量的值
        System.out.println(System.getenv("JAVA_HOME"));
        // 获取所有系统属性
        Properties properties = System.getProperties();
        try {
            // 第二个参数是文件首行的注释说明，比如下面的代码就会得到 # System properties
            properties.store(new FileOutputStream("doc/props.txt"), "System properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 指定的系统属性
        System.out.println(System.getProperty("os.name"));
    }

    /**
     * s1 s2是两个不同的对象，String重写了hashCode()方法：根据字符序列计算hashcode值
     *
     * s3 s4是同一个对象
     */
    public static void testIdentityHashCode() {
        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println(s1.hashCode() + " --- " + s2.hashCode()); // 相同
        System.out.println(System.identityHashCode(s1) + " --- " + System.identityHashCode(s2)); // 不同

        String s3 = "world";
        String s4 = "world";
        System.out.println(s3.hashCode() + " --- " + s4.hashCode()); // 相同
        System.out.println(System.identityHashCode(s3) + " --- " + System.identityHashCode(s4)); // 相同
    }

    public static void testRuntime() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("处理器数量： " + runtime.availableProcessors());
        System.out.println("空闲内存数： " + runtime.freeMemory());
        System.out.println("总内存数： " + runtime.totalMemory());
        System.out.println("可用最大内存数： " + runtime.maxMemory());

    }

    /**
     * 执行系统命令，参见 io 部分
     */
    public static void testExec() {
        try {
            System.out.println(Runtime.getRuntime().exec("javac"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
