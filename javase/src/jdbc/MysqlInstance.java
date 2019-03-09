package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 20190309
 * TODO： 这个类并没有考虑自动关闭连接，该如何优化？
 */
public class MysqlInstance {
    private Connection connection;
    private String driver;
    private String url;
    private String user;
    private String pass;

    { // 初始化块，用于加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置直接写在代码里，可以指定数据库名
     */
    public MysqlInstance(String dbName) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/" + dbName
                + "?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true";
        this.connection = DriverManager.getConnection(url, "root", "fancyChuan");
    }

    /**
     * 使用配置文件来连接msyql
     */
    public MysqlInstance(File file) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(file));
        this.url = props.getProperty("url");
        this.user = props.getProperty("user");
        this.pass = props.getProperty("pass");
        this.connection = DriverManager.getConnection(this.url, this.user, this.pass);
    }

    public Statement getBasciStatement() throws SQLException {
        return this.connection.createStatement();
    }
}
