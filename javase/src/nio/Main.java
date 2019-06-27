package nio;

import java.nio.CharBuffer;

public class Main {

    /**
     * 1. Buffer的日常使用
     */
    public static void testBuffer() {
        // 创建
        CharBuffer buff = CharBuffer.allocate(8);
        System.out.println("capacity: " + buff.capacity());
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());

        buff.put('a');
        buff.put('c');
        buff.put('b');
        System.out.println("===== 放入三个元素后 =====");
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());

        buff.flip();
        System.out.println("===== 调用flip函数后 =====");
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());

        buff.get(1); // 绝对读取，position不变，还是0
        System.out.println("===== 取出一个元素后 =====");
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());

        buff.clear();
        System.out.println("===== 调用clear函数后 =====");
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());

        buff.put('d');
        System.out.println("===== 再插入一个元素 =====");
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());
        System.out.println(buff.get(0)); // 因为position=0，这个时候把第一个元素a给覆盖掉了
        System.out.println(buff.get(1));
        System.out.println(buff.get(2));
        System.out.println(buff.get(3)); // 并不是在第4位上加入d，所以这里取出来为空，但不报错
        System.out.println("===== 插入一个元素，再获取 =====" + buff.get()); // 相对读取，这个时候因为先put，position=1，get()之后position=2
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());

    }

    public static void main(String[] args) {
        testBuffer();
    }
}
