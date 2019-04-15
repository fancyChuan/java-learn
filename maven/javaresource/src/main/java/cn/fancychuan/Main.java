package cn.fancychuan;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
//        findInsideByClassLoader();
//        findInsideDirByClassLoader();
        // findOutsideByClassLoader();
        findOutsideForJar();
    }

    // resources目录下的 sample-inside.conf
    public static void findInsideByClassLoader() {
        String path = Main.class.getClassLoader().getResource("").getPath();
        System.out.println(path);
        String fileStr = path + "sample-inside.conf";
        System.out.println(fileStr);
        readFileContent(fileStr);
    }

    // resources目录下的 conf-in/sample-inside-dir.conf
    public static void findInsideDirByClassLoader() {
        String path = Main.class.getClassLoader().getResource("").getPath();
        String fileStr = path + "conf-in/sample-inside-dir.conf";
        System.out.println(fileStr);
        readFileContent(fileStr);
    }

    /**
     * 获取maven标准路径之外的文件
     */
    public static void findOutsideByClassLoader() throws Exception {
        URL url = Main.class.getClassLoader().getResource("");
        System.out.println(url.getPath()); // 得到的路径是 /E:/javaProject/java-learn/maven/javaresource/target/classes/
        Path path = Paths.get(url.toURI());
        // 需要获取到 E:/javaProject/java-learn/maven/javaresource/ 然后找到 conf-outside
        // 也就是说，目标资源文件与当前类相隔2个目录
        String fileStr = path.getParent().getParent().toString() + "\\conf-outside\\sample-outside.conf";
        System.out.println(fileStr);
        readFileContent(fileStr);
    }

    /**
     * 通过打包成jar执行
     * 结论：
     *  1. jar是一个文件而不是文件夹，已经无法像上面一样能够去拼接路径，从而读取文件。也就是说，如果没有打包在jar里面的资源文件将无法访问
     *  2. getResourceAsStream() 可以读取到jar包中的资源文件，以流的方式访问
     *  3. Class.getResourceAsStream() 必须以 / 开头，表示jar包内的根目录，或者说首级目录 见 范例2
     *  4. ClassLoader.getResourceAsStream() 不能以 / 开头，JVM会自动在jar包内搜索 见 范例3
     */
    public static void findOutsideForJar() throws Exception {
        System.out.println("========= 在jar包中下面的四个方法返回的都是空对象 ==========");
        System.out.println(Main.class.getResource("/"));  // 得到null
        System.out.println(Main.class.getResource("")); // 得到 null
        System.out.println(Main.class.getClassLoader().getResource("/")); // 得到null
        System.out.println(Main.class.getClassLoader().getResource("")); // 得到null

        System.out.println("========== 范例2 ========= ");
        InputStream in = Main.class.getResourceAsStream("/sample-inside.conf");
        readContentFromStream(in);
        System.out.println("==========");
        InputStream in2 = Main.class.getResourceAsStream("/conf-in/sample-inside-dir.conf");
        readContentFromStream(in2);
        System.out.println("========== 范例3 ========= ");
        InputStream in3 = Main.class.getClassLoader().getResourceAsStream("sample-inside.conf");
        readContentFromStream(in3);
        System.out.println("========== ");
        InputStream in4 = Main.class.getClassLoader().getResourceAsStream("/sample-inside.conf"); // 加了 / 就找不到资源了
        readContentFromStream(in4);
    }

    /**
     * 工具方法，读取文件内容
     */
    private static void readFileContent(String pathname) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(pathname)));
            String line = null;
            while ( (line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readContentFromStream(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int hasread = 0;
        while ((hasread = inputStream.read(bytes)) > 0) {
            System.out.println(new String(bytes, 0, hasread));
        }
    }
}
