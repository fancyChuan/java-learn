package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 20190309
 * TODO： 这个类并没有考虑自动关闭连接，该如何优化？
 */
public class MysqlInstance {
    private Connection connection;

    { // 初始化块，用于加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MysqlInstance(String dbName) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/" + dbName
                + "?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true";
        this.connection = DriverManager.getConnection(url, "root", "fancyChuan");
    }

    public Statement getBasciStatement() throws SQLException {
        return this.connection.createStatement();
    }
}
