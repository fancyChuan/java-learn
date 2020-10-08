package cn.fancychuan.springboot;

import cn.fancychuan.springboot.bean.Person;
import cn.fancychuan.springboot.stepbystep.model.AyUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Autowired
	Person person;

	@Test
	public void contextLoads() {
        System.out.println(person);
    }

    //
    @Resource
    private JdbcTemplate jdbcTemplate; // 通过JDBC连接数据库的工具类
    @Test
    public void mysqlTest() {
        String sql = "select id, name, password from ay_user";
        List<AyUser> userList = jdbcTemplate.query(sql, new RowMapper<AyUser>() {
            @Override
            public AyUser mapRow(ResultSet resultSet, int i) throws SQLException {
                AyUser user = new AyUser();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        for (AyUser user : userList) {
            System.out.println("=============");
            System.out.println("[id]: " + user.getId()
                    + "\n[name]: " + user.getName()
                    + "\n[password]: " + user.getPassword());
        }
    }
}
