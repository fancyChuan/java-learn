package base.io;

import java.io.*;
import java.util.Scanner;

public class ProcessReadWrite {
    public static void main(String[] args) throws IOException {
        // testReadFromProcess();
        // ReadStandard.main(new String[0]); 这样可以调用ReadStandard类
        testWirteToProcess(); // TODO: 代码可以运行，但是内容无法写入
    }


    public static void testReadFromProcess() throws IOException {
        // 执行javac命令，返回执行该命令的子进程对象
        Process process = Runtime.getRuntime().exec("javac");
        try (
                // 对于主程序来说这里是输入流，并用BufferedReader进行包装。对于子进程则是输出流
                // GBK为指定的编码，默认是按照系统编码，但在IDEA中是utf-8，会导致与windows系统不符
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(process.getErrorStream(),"GBK"))
        ) {
            String buff = null;
            while ((buff=bufferedReader.readLine()) != null) {
                System.out.println(buff);
            }
        }
    }

    public static void testWirteToProcess() throws IOException {
        // 这里需要指定子进程执行命令时候的路径，否则会找不到编译后的类 IDEA一般会编译到target/production目录下
        Process process = Runtime.getRuntime().exec("java -cp target\\production\\javase base.io.ReadStandard");

        System.out.println(new BufferedReader(new InputStreamReader(process.getErrorStream(),"GBK")).readLine());

        try (PrintStream ps = new PrintStream(process.getOutputStream())) {
            ps.println("普通的字符串啦");
            ps.println(new ProcessReadWrite());
        }
    }
}

class ReadStandard {
    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in);
                PrintStream ps = new PrintStream(new FileOutputStream("src/base/io/process-read-write-out.txt"))
        ) {
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                String line = scanner.next();
                ps.println("键盘输入的内容是： " + line);
                if (line.equals("exit")) {
                    System.exit(1);
                }
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
