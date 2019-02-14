package base.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 20190214 转换流
 *      InputStreamReader       字节输入流->字符输入流
 *      OutputStreamWriter      字节输出流->字符输出流
 *
 *  System.in 标准输入，是InputStream的实例，使用不太方便，可以用InputStreamReader转为字符输入流
 *  普通的Reader使用也不方便，再用BufferedReader进行包装，就可以用readLine() 一次读出一行的内容
 *
 *  BufferedReader具有缓冲功能，readLine()会一直阻塞直到遇到换行符
 */
public class KeyInTest {
    public static void main(String[] args) {
        try (
                // 1.将System.in转换成Reader对象
                InputStreamReader reader = new InputStreamReader(System.in);
                // 2.把普通的Reader包装成BufferedReader
                BufferedReader br = new BufferedReader(reader)
        ) {
            String line = null;
            while ((line=br.readLine()) != null) {
                if (line.equals("exit")) {
                    System.exit(1); // 程序退出
                }
                System.out.println("输入的内容为： " + line);
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
