package base.oop;

public class Test2 {

    public static void main(String[] args) {
        byte b = 10;
        test(b);

    }

    /**
     * 基本类型的查找，当前类型没有找到，会扩大类型的查找精度。
     * 比如:
     *     byte b=10; test(b);
     *     把 test(byte b) 这个函数注释掉，那么b就会扩大到short，也就是执行 test(short s)
     *
     * 注意跟Test1的区别，方法应用的查找跟基本类型的查找不一样。
     *
     * byte     8位      -128~127
     * short    16位     -32768~32767
     * char     16位     0-65535
     * int      32位     -2147483648~2147483647
     * long     64位
     */
    public static void test(byte b) {
        System.out.println("bbbb");
    }

    public static void test(short s) {
        System.out.println("ssss");
    }

    public static void test(char c) {
        System.out.println("cccc");
    }

    public static void test(int i) {
        System.out.println("iiii");
    }
}
