package network;

import java.io.IOException;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        testInetAddress();
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
}
