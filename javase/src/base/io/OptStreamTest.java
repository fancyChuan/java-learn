package base.io;

import java.io.*;

/**
 * 20190213 处理流
 *      流的构造器不是一个物理节点，而是已经存在的流，那么就是处理流
 *
 *  StringReader/StringWriter 与FileReader/FileWriter用法相似，只是StringReader使用的是字符串节点，而不是文件节点
 */
public class OptStreamTest {
    public static void main(String[] args) {
        // testPrintStream();
        testStringNode();
    }

    public static void testPrintStream() {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("src/base/io/printStream.txt");
                PrintStream printStream = new PrintStream(fileOutputStream)
        ) {
            printStream.println("普通字符串"); // 会把内容写到printStream.txt文件中，因为这里包装的是输出流
            printStream.println(new OptStreamTest());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 以字符串做为物理节点的节点流（访问字符串）
    public static void testStringNode() {
        String src = "我有一只小毛驴\n"
                + "我从来都不骑\n"
                + "\n这是来自fancyChuan\n";
        char[] buffer = new char[32];
        int hasread = 0;
        try (StringReader stringReader = new StringReader(src)) {
            while ((hasread=stringReader.read(buffer))>0) {
                System.out.println(new String(buffer, 0, hasread));
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try (StringWriter stringWriter = new StringWriter()) {
            stringWriter.write("有一个美丽的dif \n");
            stringWriter.write("那里有天真的孩子 \n");
            System.out.println("---下面是stringWriter字符串节点里的内容---");
            System.out.println(stringWriter.toString());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
