package base;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 零散的小知识点
 */
public class LettlePoints {

    public static void main(String[] args) {
        // testToCharArray();
        // testReadFileOnce();
        // testGetBytes();
        // testStringBufferReverse();
        // testPlusPlus();
        // testPath();
        testArrayAssign();
    }

    /**
     * 1. String.toCharArray() 把字符串转为数组，每个元素分别为一个字符
     * "abc" --> ["a", "b", "c"]
     * "中文" --> ["中", "文"]
     *
     * 如果要转为字节数组，那么要使用getBytes() 方法，参见testGetBytes()
     */
    public static void testToCharArray() {
        String text = new String("中文的啊");
        char[] chars = text.toCharArray();
        for (char c: chars) {
            System.out.println(c);
        }
    }

    /**
     * 2. 一次性把输入流的所有内容读出
     *
     * 2.1. 通过File对象读出文件的元信息
     * 2.2. 获取文件的长度，并转化为int类型，file.length()是long类型的
     * 2.3. 用数组保存输入流读到的内容 InputStream.read()
     * 2.4. 用new String() 把数组转为字符串并打印出来
     */
    public static void testReadFileOnce() {
        File file = new File("src/base/io/copy.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] content = new byte[(int) file.length()];
            int hasread = fileInputStream.read(content);
            System.out.println(new String(content));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 3. 把字符串转为字节数组 getBytes()
     * 对于abc等字母来说，一个字符为一个字节，相当于是test的一个元素
     * 对于中文字符来说，一个中文为3个字节，对应test的三个元素 【utf-8编码】【gbk编码时一个中文字符为2个字节】
     */
    public static void testGetBytes() {
        byte[] test = "这是".getBytes();
        System.out.println(test);
        System.out.println("==========");
        for (byte b: test) {
            System.out.println(b);
        }
        System.out.println("==========");
        System.out.println(new String(test));
        System.out.println(test.toString());

        try {
            byte[] test2 = "这是".getBytes("GBK");
            for (byte b: test2) {
                System.out.println(b);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void testStringBufferReverse() {
        StringBuffer a = new StringBuffer("abc");
        StringBuffer b = new StringBuffer("这是");
        System.out.println(a.reverse());
        System.out.println(b.reverse());
    }

    /**
     * 4. ++a a++的区别
     * a++ 是先使用这个变量，再加1
     * ++a s是先加1，再使用这个变量
     */
    public static void testPlusPlus() {
        int a = 0;
        int b = 0;
        for (int i=0; i<10; i++) {
            System.out.print("i: " + i);
            System.out.print("\t a: " + a++); // 这里是先把a的值打印出来，再让a+1
            System.out.print("\t b: " + ++b + "\n"); // 这里是先把a+1再把a的值打印出来
        }
    }

    /**
     * 5. java相对路径、绝对路径
     */
    public static void testPath() {
        new LettlePoints().testAbsPath();
        new LettlePoints().testRelPath();
    }

    /**
     * 5.1 根据类获取绝对路径
     * 路径含包名：
     *      this.getClass().getResource("")
     *      LettlePoints.class.getResource("")
     * 路径不含包名：
     *      LettlePoints.class.getResource("/");
     *      LettlePoints.class.getClassLoader().getResource("")
     *      ClassLoader.getSystemResource("")
     *      Thread.currentThread().getContextClassLoader().getResource("")
     *
     * 对java来说，真正执行的是.class文件，也就是编译后的类文件，那么绝对路径取到的也是根据解析后的路径
     */
    public void testAbsPath() {
        URL base1 = this.getClass().getResource("");
        System.out.println(base1); // file:/E:/javaProject/java-learn/javase/target/production/javase/base/
        URL base2 = ClassLoader.getSystemResource("");
        System.out.println(base2); // file:/E:/javaProject/java-learn/javase/target/production/javase/
        URL base3 = LettlePoints.class.getResource(""); // 跟base1的效果是一样的
        System.out.println(base3); // file:/E:/javaProject/java-learn/javase/target/production/javase/base/
        URL base4 = LettlePoints.class.getResource("/");
        System.out.println(base4); // file:/E:/javaProject/java-learn/javase/target/production/javase/
        URL base5 = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.println(base5); // file:/E:/javaProject/java-learn/javase/target/production/javase/
        URL base6 = LettlePoints.class.getClassLoader().getResource("");
        System.out.println(base6); // file:/E:/javaProject/java-learn/javase/target/production/javase/
    }
    public void testRelPath() {

    }

    /**
     * 6. java数组赋值细节
     */
    public static void testArrayAssign() {
        String[] args = {"hello", "world", "hhh"};
        Object[] objs0 = args;
        Object objs1[] = args;
        Object objs2[] = {args}; // 把整个args数据对象作为objs2的一个元素
        Object[] objs3 = {args}; // 上一种新建数组跟这一个是一样的，都是把整个args数据对象作为新数组的一个元素
        System.out.println(args); // [Ljava.lang.String;@4554617c
        System.out.println(objs0); // [Ljava.lang.String;@4554617c
        System.out.println(objs1); // [Ljava.lang.String;@4554617c
        System.out.println(objs2); // [Ljava.lang.Object;@74a14482
        System.out.println(objs3); // [Ljava.lang.Object;@424c0bc4
    }

    /**
     * 7. 引用对象的赋值，要注意操作真正会影响哪些地方。
     * 下面这个例子只是浅拷贝。   “细说java深拷贝、浅拷贝”  https://www.cnblogs.com/plokmju/p/7357205.html
     */
    @Test
    public void testObjectAssign() {
        HashMap<Integer, Integer> mainMap = new HashMap<>();

        Map nowMap = mainMap; // 这里nowMap和mainMap都是引用对象，真正对象的存储地址比如为760
        mainMap.put(-1, -1); // mainMap修改会反映到nowMap，因为他们其实操作的是同一个对象
        nowMap.put(0, 0); // nowMap的修改也会影响mainMap，也就是存储地址为760的对象

        nowMap = new HashMap(); // 这个地方nowMap这个对象就发生变化了，也就是nowMap所引用的真正对象的存储地址变为了比如770，但mainMap不受影响
        nowMap.put(1, 1); // 只影响地址为770的对象，也就是说mainMap还是不受影响
        System.out.println(mainMap);
        System.out.println(nowMap);
    }

    /**
     * 8. 查看浮点数的二进制表示
     */
    @Test
    public void testFloatBit() {
        // 1000000110000000000000000000000
        // 1000000111000000000000000000000
        // 11000000111000000000000000000000
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(6)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(7)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(-7)));
        // 100000000100110000000000000000000000000000000000000000000000000
        System.out.println(Long.toBinaryString(Double.doubleToLongBits(11)));
    }
}
