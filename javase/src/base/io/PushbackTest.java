package base.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 * 20190214 推回输入流实例：打印出目标字符串之前的所有内容
 *
 * 推回输入流的应用场景：对每次读取的内容进行部分操作。比如取出32个字节，但是可能只需要前面14个字节
 *
 * - unread(byte[]/char[] buf)
 * - unread(byte[]/char[] buf, int off, int len)
 * - unread(int b)
 */
public class PushbackTest {
    public static void main(String[] args) {
        try (
                FileReader fileReader = new FileReader("src/base/io/PushbackTest.java");
                // 包装输入流，设置推回缓冲区的长度为64
                PushbackReader pushback = new PushbackReader(fileReader, 64)
        ) {
           char[] buf = new char[32];
           String lastContent = "";
           int hasread = 0;
           while ((hasread=pushback.read(buf))>0) {
               String content = new String(buf, 0, hasread);
               int targetIndex = 0;

               if ((targetIndex = (lastContent + content).indexOf("new PushbackReader")) > 0) {
                   // 把两部分字符串推回缓冲区
                   pushback.unread((lastContent + content).toCharArray());
                   if (targetIndex>32) {
                       buf = new char[targetIndex];
                   }
                   // 这个时候会从缓冲区先读取数据，不够了以后才会到原输入流中读取内容
                   pushback.read(buf, 0, targetIndex);

                   System.out.print(new String(buf, 0, targetIndex));
                   System.exit(1);
               }
               else {
                   System.out.print(lastContent);
                   lastContent = content;
               }
           }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
