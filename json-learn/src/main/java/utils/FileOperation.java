package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 */
public class FileOperation {
    /**
     * 追加写入文件，用FileWriter实现
     */
    public static void write2file(String path, String data, String mode) throws IOException {
        if (mode.equals("append") || mode.equals("a+")) {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(data);
            System.out.println("文件追加写入本地文件成功！");
            fileWriter.close();
        } else {
            System.out.println("请提供正确的写入模式参数！append或者a+");
        }
    }

    /**
     * 覆盖写入文件，用FileOutputStream实现
     */
    public static void write2file(String path, String data) throws IOException {
        FileOutputStream stream = new FileOutputStream(new File(path));
        stream.write(data.getBytes());
        System.out.println("文件覆盖写入本地文件成功！");
        stream.close();
    }

    public static List<String> readFile(String path) throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        BufferedReader buffer = new BufferedReader(new FileReader(new File(path)));
        while (buffer.ready()) { // 判断是否有下一行
            strings.add(buffer.readLine());
        }
        return strings;
    }

    public static void main(String[] args) throws IOException {
        String path = "E:\\JavaWorkshop\\java-learn\\json-learn\\src\\main\\java\\utils\\test.txt";
        write2file(path, "\nwhat?\nAre you crazy", "append");
        System.out.println(readFile(path));
    }
}
