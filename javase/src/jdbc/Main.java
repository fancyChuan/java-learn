package jdbc;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        // testConnMysql();
        testExecuteType();
    }

    public static void testConnMysql() throws ClassNotFoundException {
        // 1. 加载驱动，使用反射只是
        Class.forName("com.mysql.jdbc.Driver");
        try (
            // 2. 使用DriverManager获取数据库连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jianshu",
                    "root", "fancyChuan");
            // 3. 使用连接创建一个Statement对象用于执行SQL
            Statement stmt = conn.createStatement();
            // 4. 执行SQL
            ResultSet rs = stmt.executeQuery("select * FROM comment limit 5, 3");
            // rs.next() 用于移动指针并判断是否指向有效行，有效则返回true，那么rs.getXxx()就可以获取数据
        ) {
            while (rs.next()) {
                // 0000a2c049e4	3	3b28d5250f88	phliny	0	2016-09-03 19:49:00	好犀利	2016-09-10 14:59:54
                // getXxx() 方法有两种参数，一种是列的索引，从1开始；另一种是列名
                // getXxx() 其实应该理解为，取出列的值后把它转为Xxx类型
                System.out.print(rs.getString(1)); // 索引从1开始
                System.out.print("\t" + rs.getString(2)); // 因为所有数据都可以视为String，所以第2列的数字3也会被取出来
                System.out.print("\t" + rs.getString(6)); // 时间也一样为string，可以取出
                // System.out.println("\t" + rs.getInt(1)); // 第1为是0000a2c049e4，为字符串，无法取出
                System.out.print("\t" + rs.getInt(2));
                System.out.print("\t" + rs.getString("comName"));
                System.out.println("\t" + rs.getString("comcontent"));
                System.out.println("-------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testExecuteType() {
        try (
                Statement stmt = new MysqlInstance("for_learn").getBasciStatement()
        ) {
            // executeUpdate() 支持DDL DML语句
            int num = stmt.executeUpdate("CREATE TABLE if NOT EXISTS tmp1 (id int, name VARCHAR(20))");
            System.out.println(num); // DDL语句返回0
            int num2 = stmt.executeUpdate("INSERT INTO tmp1 VALUES (1, 'fancy'), (2, 'Chuan'), (3, '哇哇哇')");
            System.out.println(num2); // DML语句返回影响的条数

            // execute执行结果有返回值，那么为true，负责为false
            System.out.println("=============");
            Boolean rs2 = stmt.execute("show tables");
            System.out.println(rs2);
            Boolean rs3 = stmt.execute("SELECT count(*) FROM tmp1");
            System.out.println(rs3);
            Boolean rs4 = stmt.execute("DROP TABLE if EXISTS tmp;");
            System.out.println(rs4);

//            System.out.println("=============");
//            ResultSet rs5 = stmt.executeQuery("CREATE TABLE tmp1 (id int)"); // 非select语句执行报错
//            System.out.println(rs5.next());


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
