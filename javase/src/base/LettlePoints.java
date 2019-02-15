package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 零散的小知识点
 */
public class LettlePoints {

    public static void main(String[] args) {
        // testToCharArray();
        testReadFileOnce();
    }

    /**
     * String.toCharArray() 把字符串转为数组，每个元素分别为一个字符
     * "abc" --> ["a", "b", "c"]
     * "中文" --> ["中", "文"]
     */
    public static void testToCharArray() {
        String text = new String("中文的啊");
        char[] chars = text.toCharArray();
        for (char c: chars) {
            System.out.println(c);
        }
    }

    /**
     * 一次性把输入流的所有内容读出
     *
     * 1. 通过File对象读出文件的元信息
     * 2. 获取文件的长度，并转化为int类型，file.length()是long类型的
     * 3. 用数组保存输入流读到的内容 InputStream.read()
     * 4. 用new String() 把数组转为字符串并打印出来
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
}
