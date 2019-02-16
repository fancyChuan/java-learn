package base.io;

import java.io.*;

public class RondomAccessFileTest {

    public static void main(String[] args) {
        // testReadGivenPointer();
        testAppendContent();
        try {
            testInsertContext("src/base/io/writer.txt", 6, "【这是指定位置插入的啊】");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从指定位置读取文件内容
    public static void testReadGivenPointer() {
        try (RandomAccessFile raf = new RandomAccessFile("src/base/io/RondomAccessFileTest.java", "r")) {
            System.out.println("指针位置：" + raf.getFilePointer());

            raf.seek(300);
            byte[] buf = new byte[1024];
            int hasread = 0;
            while ((hasread=raf.read(buf))>0) {
                System.out.println(new String(buf, 0, hasread));
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 向文件追加内容，关键是把指针移动到文件末尾
     * RandomAccessFile有length()方法可以获取文件大小，
     * 而如果使用FileInputStream的话，就只能通过File去获取文件大小。参见 base.LettlePoints.java
     */
    public static void testAppendContent() {
        try (RandomAccessFile raf = new RandomAccessFile("src/base/io/writer.txt", "rw")) {
            raf.seek(raf.length());
            raf.write("追加的内容！".getBytes("utf-8"));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 想指定点插入内容，需要注意直接插入的时候，会覆盖
     */
    public static void testInsertContext(String fileName, long pos, String insertContent) throws IOException {
        File tmp = File.createTempFile("src/base/io/tmp", null);
        tmp.deleteOnExit();
        try (
                RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
                FileOutputStream tmpOutput = new FileOutputStream(tmp);
                FileInputStream tmpInput = new FileInputStream(tmp)

        ) {
            // 1. 把插入点后面的内容存到临时文件
            randomAccessFile.seek(pos);
            byte[] moveOut = new byte[1024];
            while (randomAccessFile.read(moveOut)>0) {
                tmpOutput.write(moveOut);
            }
            // 2.这里需要重新设置指针的位置并插入内容
            randomAccessFile.seek(pos);
            randomAccessFile.write(insertContent.getBytes("UTF-8"));
            // 3. 把临时文件的内容重新追加到原文件
            byte[] moveIn = new byte[64];
            while (tmpInput.read(moveIn)>0) {
                randomAccessFile.write(moveIn);
            }
        }
    }
}
