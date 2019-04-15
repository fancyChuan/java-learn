package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件操作工具类
 */
public class FileOperation {
    public static void write2file(String path, String data, String mode) throws IOException {
        File file = new File(path);
        if (mode.equals("append")) {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(data);
            System.out.println("文件追加写入本地文件成功！");
            fileWriter.close();
        } else {
            FileOutputStream stream = new FileOutputStream(new File(path));
            stream.write(data.getBytes());
            System.out.println("文件覆盖写入本地文件成功！");
            stream.close();
        }
    }

    public static void main(String[] args) throws IOException {
        write2file("E:\\JavaWorkshop\\java-learn\\json-learn\\src\\main\\java\\utils\\test.txt",
                "\nwhat?\nAre you crazy", "append");
    }
}
