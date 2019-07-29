package network;

import network.url.DownUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Main {
    public static void main(String[] args) throws Exception {
        // testInetAddress();
        // testURLDecoderEncoder();
        testMultiThreadDown();
    }

    /**
     * 1. InetAddress类的使用
     */
    public static void testInetAddress() throws Exception {
        InetAddress ip = InetAddress.getByName("www.baidu.com");
        System.out.println("是否可达：" + ip.isReachable(2000));
        System.out.println(ip.getHostAddress());
        System.out.println(ip.getHostName());
        System.out.println(ip.getCanonicalHostName());
        System.out.println("===========");

        InetAddress local = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
        System.out.println("本机是否可达: " + local.isReachable(3000));
        System.out.println(local.getCanonicalHostName());
    }

    /**
     * 2. URlDecoder、URLEncoder的使用
     */
    public static void testURLDecoderEncoder() throws UnsupportedEncodingException {
        String content = "疯狂啊，学java啊";
        String encoded = URLEncoder.encode(content);
        String encoded2 = URLEncoder.encode(content, "gbk");
        System.out.println(encoded);
        System.out.println(encoded2);

        System.out.println(URLDecoder.decode(encoded)); // 默认使用utf8解码
        System.out.println(URLDecoder.decode(encoded, "gbk")); // 用的gbk编码，又用utf8解码会得到乱码
        System.out.println(URLDecoder.decode(encoded2, "gbk"));
    }

    /**
     * 3. 多线程下载
     */
    public static void testMultiThreadDown() throws Exception {
        DownUtil downUtil = new DownUtil("http://pic1.win4000.com/wallpaper/2017-11-17/5a0e76949f5f1.jpg", "multi.jpg", 4);
        downUtil.download();
        new Thread(() -> { // TODO：这里为什么要开启一个新的线程？？
            while (downUtil.getCompleteRate() < 1) {
                System.out.println("已完成： " + downUtil.getCompleteRate());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
