package jdbc;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 临时测试用
 */
public class TempTest {
    @Test
    public void testCharset() throws SQLException {
        Statement stmt = new MysqlInstance("forupload").getBasciStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT `产品类型`,COUNT(1) FROM invest_detail GROUP BY `产品类型`");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
            System.out.println("---------");
        }
    }
}
