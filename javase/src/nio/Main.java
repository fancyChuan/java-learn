package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

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

    /**
     * 2. FileChannel的简单应用
     */
    public static void testChannel() {
        File file = new File("src/nio/test-channel-read.txt");

        try (
            FileChannel inChannel = new FileInputStream(file).getChannel();
            FileChannel outChannel = new FileOutputStream("src/nio/test-channel-out.txt").getChannel();
        ) {
            // 把所有FileChannel里的数据都映射成Buffer对象
            // MappedByteBuffer byteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length() - 4 * 3);
            // 把所有FileChannel里的数据去掉12个字节后映射成Buffer对象，utf8编码对应4个中文，或者3个中文3个字母
            MappedByteBuffer byteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length() - 4 * 3);
            outChannel.write(byteBuffer); // 把buffer写到输出管道中
            byteBuffer.clear(); // 重置buffer，复原limit/position位置

            // 使用指定字符集来创建解码器
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder(); // 创建解码器
            CharBuffer charBuffer = decoder.decode(byteBuffer); // 对buffer字节解码成CharBuffer
            System.out.println(charBuffer);
            System.out.println("========");
            // 从Channel中读取10*3个字节
            ByteBuffer buffer = ByteBuffer.allocate(10 * 3);
            inChannel.read(buffer);
            buffer.flip(); // 从inChannel读完数据后，buffer需要调用该方法，把position置为0
            CharBuffer charBuffer1 = decoder.decode(buffer);
            System.out.println(charBuffer1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // testBuffer();
        testChannel();
    }
}
