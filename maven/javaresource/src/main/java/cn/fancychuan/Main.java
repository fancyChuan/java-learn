package cn.fancychuan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        findInsideByClassLoader();
        findInsideDirByClassLoader();
        findOutsideByClassLoader();
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
        String fileStr = path.getParent().getParent().toString() + "\\conf-outside\\sample-outside.conf";
        System.out.println(fileStr);
        readFileContent(fileStr);
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
}
