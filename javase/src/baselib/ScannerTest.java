package baselib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        // testKeyBoardScanner();
        // testHasNextLine();
        // testNextLong();
        testFileScanner();
    }

    /** 默认使用 空格、tab、回车 作为分隔符
     * 运行结果：
     *      a b c
     *      刚才输入了： a
     *      刚才输入了： b
     *      刚才输入了： c
     */
    public static void testKeyBoardScanner() {
        Scanner scanner = new Scanner(System.in);
        // scanner.useDelimiter("\n"); // 加上这一行就只把\n作为分隔符
        while (scanner.hasNext()) {
            String content = scanner.next();
            if (content.equals("exit")) {
                System.exit(0);
            }
            System.out.println("刚才输入了： " + content);
        }
    }

    /**
     * nextLine()就只会把\n作为分隔符
     * 运行结果：
     *      a b c
     *      刚才输入了： a b c
     */
    public static void testHasNextLine() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String content = scanner.nextLine();
            if (content.equals("exit")) {
                System.exit(0);
            }
            System.out.println("刚才输入了： " + content);
        }
    }

    // 只允许输入Long类型，否则就退出
    public static void testNextLong() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLong()) {
            System.out.println("刚才输入了： " + scanner.nextLong());
        }
    }

    public static void testFileScanner() {
        try {
            Scanner scanner = new Scanner(new File("src/baselib/ScannerTest.java"));
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
