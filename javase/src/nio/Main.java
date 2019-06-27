package nio;

import java.nio.CharBuffer;

public class Main {

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
        System.out.println("===== 插入一个元素，再获取 =====" + buff.get()); // 相对读取，这个时候因为先put，position=1，get()之后position=2
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());

    }

    public static void main(String[] args) {
        testBuffer();
    }
}
