package base.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class Redirect {
    public static void main(String[] args) {
        // testRedirectOut();
        testRedirectIn();
    }


    /**
     * 将输出重定向到文件中
     */
    public static void testRedirectOut() {
        try (PrintStream ps = new PrintStream(new FileOutputStream("src/base/io/redirectout.txt"))) {
            // 将标准输出重定向到ps输出流
            System.setOut(ps);
            System.out.println("这个本来是要输出到屏幕的!");
            System.out.println(new Redirect());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * 将标准输入重定向到文件输入流
     */
    public static void testRedirectIn() {
        try (FileInputStream fis = new FileInputStream("src/base/io/Redirect.java")) {
            // 将标准输入重定向到文件输入流，也就是使用fis代替键盘输入
            System.setIn(fis);

            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) {
                System.out.println("键盘输入的内容是： " + scanner.next());
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
