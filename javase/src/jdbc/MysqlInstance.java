package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Properties;

/**
 * 20190309
 * TODO： 这个类并没有考虑自动关闭连接，该如何优化？
 * 需要让这个类是 AutoCloseable 这样才能在try(){}catch(Exception e) {}结构中自动关闭
 */
public class MysqlInstance implements AutoCloseable {
    private Connection connection;
    private String driver;
    private String url;
    private String user;
    private String pass;

    { // 初始化块，用于加载驱动
        try {
            System.out.println("初始化块，加载驱动器");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置直接写在代码里，可以指定数据库名
     */
    public MysqlInstance(String dbName) throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/" + dbName
//                + "?useUnicode=true&characterEncoding=UTF8&createDatabaseIfNotExist=true";
        String url = "jdbc:mysql://hphost:3306/forupload?useUnicode=true&characterEncoding=UTF8&allowLoadLocalInfile=false&autoDeserialize=false&allowLocalInfile=false&allowUrlInLocalInfile=false";
        this.connection = DriverManager.getConnection(url, "root", "123456");
    }

    /**
     * 使用配置文件来连接mysql
     */
    public MysqlInstance(File file) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(file));
        this.url = props.getProperty("url"); // 注意文件中a=b不能写成a="b"否则java得到的a=""b""
        this.user = props.getProperty("user");
        this.pass = props.getProperty("pass");
        this.connection = DriverManager.getConnection(this.url, this.user, this.pass);
    }

    /**
     * 1. 获取基本的Statement对象
     */
    public Statement getBasciStatement() throws SQLException {
        return this.connection.createStatement();
    }

    /**
     * 2. 预编译的Statement对象
     */
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }
    // 这里需要用到多态，python中的函数有位置参数、默认参数、关键词参数、不定参数，而java没有这种概念
    public PreparedStatement getPreparedStatement(String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
        return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }
    public PreparedStatement getPreparedStatement(String sql, int resultSetType)
            throws SQLException {
        return this.connection.prepareStatement(sql, resultSetType);
    }


    /**
     * 3. 可以调用存储过程的CallableStatement对象
     */
    public CallableStatement getCallableStatement(String sql) throws SQLException {
        return this.connection.prepareCall(sql);
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
