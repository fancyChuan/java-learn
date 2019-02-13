package base.io;

import java.io.File;

/*
========运行结果=========
FilenameFilterTest.java
FileTest.java
 */
public class FilenameFilterTest {

    public static void main(String[] args) {
        File file = new File("src/base/io");
        // 使用Lambda表达式实现accept方法
        String[] nameList = file.list((dir, name) -> name.endsWith(".java") || new File(name).isDirectory());
        for (String name:nameList) {
            System.out.println(name);
        }
    }
}
