package jdbc;

import java.io.File;
import java.sql.*;

/**
 * 20190309 JDBC
 * 1. JDBC的使用步骤                     testConnMysql()
 * 2. 测试Statement的三种执行方法          testExecuteType()
 * 3. 测试通过文件初始化数据库连接            testInitUseFile()
 * 4. 测试PreparedStatement用法与性能      testPreparedStatement();
 * 5. CallableStatement用法               testCallableStatement()
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        // testConnMysql();
        // testExecuteType();
        // testInitUseFile();
        // testPreparedStatement();
        testCallableStatement();
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

    /**
     * execute的三种执行方式
     */
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
            if (rs3) {
                ResultSet rsrs = stmt.getResultSet();
                rsrs.next();
                System.out.println("表的记录数为：" + rsrs.getInt(1));
            }
            System.out.println(rs3);

            stmt.execute("INSERT INTO tmp1 VALUES (1, 'fancy'), (2, 'Chuan'), (3, '哇哇哇')"); // 没有ResultSet返回
            System.out.println("受影响的记录数：" + stmt.getUpdateCount()); // 获取受影响的记录数

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

    public static void testInitUseFile() {
        try (
            Statement stmt = new MysqlInstance(new File("src/jdbc/jdbc-mysql.properties")).getBasciStatement()
        ) {
            ResultSet num = stmt.executeQuery("SELECT count(*) FROM tmp1");
            System.out.println(num);
            num.next(); // 需要把指针移动才能取数据
            System.out.println(num.getInt(1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * PreparedStatement用法与性能
     *
     * 1. PreparedStatement.execute/executeQuery/executeUpdate都不需要参数
     * 2. PreparedStatement效率比Statement要高
     */
    public static void testPreparedStatement() {
        String sql = "insert into tmp1 values (?, ?)";

        try (
                // 下面这一行需要让MySQLInstance实现AutoCloseable接口，并实现close()方法，因为在try用法中，普通的对象不能放在自动关闭的方法体中
                MysqlInstance instance = new MysqlInstance("for_learn");
                Statement stmt = instance.getBasciStatement();
                PreparedStatement stmtPre = instance.getPreparedStatement(sql)
        ) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                stmt.executeUpdate("INSERT INTO tmp1 VALUES (" + i + ",'普通" + i + "')");
            }
            System.out.println("使用Statement耗时：" + (System.currentTimeMillis() - start)); // 4814
            long start1 = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                stmtPre.setInt(1, i);
                stmtPre.setString(2, "预编译" + i);
                stmtPre.executeUpdate();
            }
            System.out.println("使用PreparedStatement耗时：" + (System.currentTimeMillis() - start1)); // 3928
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * CallableStatement的用法
     */
    public static void testCallableStatement() {
        /* 在MySQL执行下面的创建存储过程的语句

dilimiter //
CREATE PROCEDURE add_pro(a int, b int, out sum int)
begin set sum = a + b;
end;
//
         */
        String sql = "{call add_pro(?, ?, ?)}";
        try(
                CallableStatement cstmt = new MysqlInstance("for_learn").getCallableStatement(sql)
        ) {
            cstmt.setInt(1, 4);
            cstmt.setInt(2, 5);
            cstmt.registerOutParameter(3, Types.INTEGER); // 注册输出的类型
            cstmt.execute();
            System.out.println("存储过程执行结果：" + cstmt.getInt(3));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
