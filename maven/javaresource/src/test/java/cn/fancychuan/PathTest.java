package cn.fancychuan;

import org.junit.Test;

public class PathTest {

    /**
     * 测试Class获取资源文件的路径情况
     * 结论：
     *  1. Class.getResouce() 得到是URL对象，打印的格式为 file:/xxx/.../  其中file表示本地文件系统
     *  2. Class.getResouce().getPath() 得到是String对象，打印的格式为 /xxx/.../  也就是本地的绝对路径
     *  3. Class.getResource("") 获得当前类所在的路径，含包名
     *  4. Class.getResource("/") 获得根目录，没有包名
     *  5. TODO：奇怪的是，为什么获取不过是App.class还是AppTest.class获取的路径的跟目录都是 test-classes ？
     */
    @Test
    public void testClassGetResource() {
        System.out.println(App.class.getResource(""));
        System.out.println(App.class.getResource("").getPath());
        System.out.println(App.class.getResource("/").getPath());

        System.out.println("=============");
        // 对于测试包下面的class文件
        System.out.println(AppTest.class.getResource("").getPath());
        System.out.println(AppTest.class.getResource("/").getPath());
        /* 运行结果
        file:/E:/javaProject/java-learn/maven/javaresource/target/test-classes/cn/fancychuan/
        /E:/javaProject/java-learn/maven/javaresource/target/test-classes/cn/fancychuan/
        /E:/javaProject/java-learn/maven/javaresource/target/test-classes/
        =============
        /E:/javaProject/java-learn/maven/javaresource/target/test-classes/cn/fancychuan/
        /E:/javaProject/java-learn/maven/javaresource/target/test-classes/
         */
    }

    /**
     * 测试ClassLoader获取资源文件路径的特点
     * 结论：
     *  1. ClassLoader.getResource() 获取路径是从跟目录开始的，所以 ClassLoader().getResource("/") 这个是错误的
     *  2. ClassLoader().getResource("") 获取到的是根目录
     */
    @Test
    public void testClassLoaderGetResource() {
        System.out.println(App.class.getClassLoader().getResource(""));
        System.out.println(App.class.getClassLoader().getResource("").getPath());
        // System.out.println(App.class.getClassLoader().getResource("/").getPath()); // 这行代码是错的
        System.out.println("===========");
        System.out.println(AppTest.class.getClassLoader().getResource("").getPath());
        /* 运行结果
        file:/E:/javaProject/java-learn/maven/javaresource/target/test-classes/
        /E:/javaProject/java-learn/maven/javaresource/target/test-classes/
        ===========
        /E:/javaProject/java-learn/maven/javaresource/target/test-classes/
         */
    }
}
