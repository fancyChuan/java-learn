package base.io;

import java.io.*;

/**
 * 20190213 抽象基类InputStream/Reader/OutputStream/Writer的案例
 *
 * java7以后实现了AutoClosable接口，使用try语句可以自动关闭流
 */
public class FileInputOutputTest {
    public static void main(String[] args) throws Exception {
        // testFileInputStream();
        // testFileReader();
        // testFileOutputStream();
        testFileWriter();
    }


    /**
     * 使用 FileInputStream 读出本java文件的内容
     *
     * String()的用法： new String(bbuf, 0, hasread)
     */
    public static void testFileInputStream() throws IOException {
        FileInputStream fis = new FileInputStream("src/base/io/FileInputOutputTest.java");
        // 创建一个长度为1024的“竹筒”用来取水
        byte[] bbuf = new byte[1024];
        // 用来保存每次读取到的字节数
        int hasread = 0;
        while ((hasread=fis.read(bbuf))>0) { // 这行代码的意思是把fis.read()读到的字节数赋值给hasread并，让hasread跟0相比。同时把读到的字节内容存到bbuf数组中
            // 将字节转处理为字符串
            System.out.print(new String(bbuf, 0, hasread));
        }
        fis.close();
    }

    /**
     * 使用 FileReader
     */
    public static void testFileReader() throws IOException {
        try (FileReader fileReader = new FileReader("src/base/io/FileInputOutputTest.java")) {
            char[] cbuf = new char[32];
            int hasread = 0; // 保存实际读取的字符数
            while ((hasread=fileReader.read(cbuf))>0) {
                System.out.print(new String(cbuf, 0, hasread));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testFileOutputStream() {
        try (
            FileInputStream fis = new FileInputStream("src/base/io/FileInputOutputTest.java");
            FileOutputStream fileOutputStream = new FileOutputStream("src/base/io/copy.txt")
        ) {
            byte[] bbuf = new byte[32];
            int hasread = 0;
            while ((hasread=fis.read(bbuf))>0) {
                // 读出多少，写入多少
                fileOutputStream.write(bbuf, 0, hasread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testFileWriter() {
        try (FileWriter fileWriter = new FileWriter("src/base/io/writer.txt")) {
            fileWriter.write("直接输入字符串的时候，");
            fileWriter.write("使用Writer会更好\n");
            fileWriter.write("哦\n");
            fileWriter.write("是吗\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
