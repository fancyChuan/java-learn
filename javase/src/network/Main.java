package network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Main {
    public static void main(String[] args) throws Exception {
        // testInetAddress();
        testURLDecoderEncoder();
    }


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
}
