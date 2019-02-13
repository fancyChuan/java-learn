package base.io;

import java.io.File;

/*
===========运行结果============
输入：.
 getName: .
 getPath: .
 getParent: null
 getAbsoluteFile: E:\JavaWorkshop\java-learn\javase\.
 getAbsolutePath: E:\JavaWorkshop\java-learn\javase\.
 getAbsoluteFile+getParent: E:\JavaWorkshop\java-learn\javase
-------
输入：test.txt
 getName: test.txt
 getPath: test.txt
 getParent: null
 getAbsoluteFile: E:\JavaWorkshop\java-learn\javase\test.txt
 getAbsolutePath: E:\JavaWorkshop\java-learn\javase\test.txt
 getAbsoluteFile+getParent: E:\JavaWorkshop\java-learn\javase
-------
输入：E:\JavaWorkshop\java-learn\javase
 getName: javase
 getPath: E:\JavaWorkshop\java-learn\javase
 getParent: E:\JavaWorkshop\java-learn
 getAbsoluteFile: E:\JavaWorkshop\java-learn\javase
 getAbsolutePath: E:\JavaWorkshop\java-learn\javase
 getAbsoluteFile+getParent: E:\JavaWorkshop\java-learn
-------


 */
public class FileTest {
    public static void main(String[] args) {

        testFileApi(".");
        testFileApi("test.txt"); // 这里对于name来说，没有上一级目录，返回null
        testFileApi("E:\\JavaWorkshop\\java-learn\\javase");
    }

    public static void testFileApi(String name) {
        File file = new File(name);
        System.out.println("输入：" + name);

        System.out.println(" getName: " + file.getName());
        System.out.println(" getPath: " + file.getPath());
        System.out.println(" getParent: " + file.getParent());
        System.out.println(" getAbsoluteFile: " + file.getAbsoluteFile());
        System.out.println(" getAbsolutePath: " + file.getAbsolutePath());
        System.out.println(" getAbsoluteFile+getParent: " + file.getAbsoluteFile().getParent());

        System.out.println("-------");
    }
}
