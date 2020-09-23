package cn.fancychuan.springboot.chap3.main;

public class DoTheTest {
    public static void main(String[] args) {
        String name = "xxx-yy-zz-yyy";
        String[] items = name.split("-", 2);
        System.out.println(items.length);
        System.out.println(items[0]);
        System.out.println(items[1]);
        System.out.println(items[2]);
    }
}
