package reflect;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

public class TestURLClassLoader {

    private static Connection connection;

    public static Connection getConnection(String url, String user, String pass) throws Exception {
        if (connection == null) {
            // 创建一个数组，从任意地方读取mysql驱动文件，这样一来就不需要吧驱动放在 classpath 里面了
            URL[] urls = {new URL("file:E:\\JavaWorkshop\\java-learn\\javase\\jar\\mysql-connector-java-5.1.46.jar")};
            // 以默认的ClassLoader作为父ClassLoader，创建URLClassLoader对象
            URLClassLoader myClassLoader = new URLClassLoader(urls);
            // 指定了父ClassLoader。从类的加载机制可以知道，URLClassLoader会优先用指定的父类加载器去加载
            // URLClassLoader exactClassLoader = new URLClassLoader(urls, new CompileClassLoader());

            Driver driver = (Driver) myClassLoader.loadClass("com.mysql.jdbc.Driver").newInstance();

            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", pass);
            connection = driver.connect(url, props);
        }
        return connection;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(TestURLClassLoader.getConnection(
                "jdbc:mysql://localhost:3306/mysql",
                "root", "root123"));
    }
}
