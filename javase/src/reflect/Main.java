package reflect;

import java.net.URL;

public class Main {
    public static void main(String[] args) {
        testBootstrap();
    }

    /**
     * 1. 根类加载器所价值的核心类库
     */
    public static void testBootstrap() {
        // 获取根类加载器所加载的类库所在的URL
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
        /*
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/resources.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/rt.jar                 String/System等核心类库都在rt.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/sunrsasign.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/jsse.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/jce.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/charsets.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/lib/jfr.jar
        file:/D:/ProgramData/jdk1.8.0_66/jre/classes
        */
    }
}
