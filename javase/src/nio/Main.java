package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

/**
 * 1. Buffer的使用
 * 2. FileChannel的简单使用
 * 3. FileChannel可读可写，取决于从哪种类型的文件流创建。（RandomAccessFile）
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // testBuffer();
        // testChannel();
        // testRandomAccessFileChannel();
        testCharset();
    }

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
     *  * Channel也是可以设置position
     *  * Channel.map()把数据映射到虚拟内存中
     *  * read、write的使用
     */
    public static void testChannel() {
        File file = new File("src/nio/test-channel-read.txt");
        File readWriteFile = new File("src/nio/test-channel-read-write.txt");

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

            // 使用RandomFileChannel 进行读写
            RandomAccessFile rwf = new RandomAccessFile(readWriteFile, "rw");
            FileChannel randomChannel = rwf.getChannel();
            MappedByteBuffer byteBufferAll = randomChannel.map(FileChannel.MapMode.READ_WRITE, 0, readWriteFile.length());
            // channel也有position(）这个方法可以设置位置，下面这行把position设置到文件结尾处
            randomChannel.position(readWriteFile.length());
            randomChannel.write(byteBufferAll); // 把读出来的数据再写一份回去

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3. FileChannel可读可写，取决于从哪种类型的文件流创建
     *  FileInputStream   ->  FileChannel 只可读
     *  FileoutputStream  ->  FileChannel 只可写
     *  RandomAccessFile  ->  FileChannel 可读可写
     */
    public static void testRandomAccessFileChannel() throws IOException {
        File file = new File("E:\\JavaWorkshop\\java-learn\\javase\\src\\nio\\test-channel-randomAccess.txt");
        try (
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                FileChannel randomChannel = raf.getChannel()
        ) {
            // 把所有数据映射成ByteBuffer
            MappedByteBuffer byteBuffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            randomChannel.position(file.length()); // 把指针移到末尾
            randomChannel.write(byteBuffer); // 把Buffer中的所有数据写入文件
        }
    }

    public static void testCharset() throws Exception {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String alias : map.keySet()) {
            System.out.println(alias + " ---> " + map.get(alias));
        }
        System.out.println("==================================");
        System.out.println("本地文件系统的编码：" + System.getProperty("file.encoding")); // 注意在idea可能是utf-8，在本地命令行执行则是GBK
        System.out.println("==================================");
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();
        CharBuffer buffer = CharBuffer.allocate(8);
        buffer.put("猴");
        buffer.put("子");
        buffer.flip();
        ByteBuffer encodedBuffer = encoder.encode(buffer); // 将字符序列转为字节序列
        for (int i = 0; i < encodedBuffer.limit(); i++) {
            System.out.print(encodedBuffer.get(i) + " ");
        }
        System.out.println("\n" + decoder.decode(encodedBuffer)); // 重新解码为字符序列
    }

}
