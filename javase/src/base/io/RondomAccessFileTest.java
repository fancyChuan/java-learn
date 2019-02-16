package base.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RondomAccessFileTest {

    public static void main(String[] args) {
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
}
