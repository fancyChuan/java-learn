package cn.fancychuan.dao;

import cn.fancychuan.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository // 定义为一个DAO
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName, String password) {
        String sqlStr = "select count(*) from t_user where user_name=? and password=?";
        int result = jdbcTemplate.queryForObject(sqlStr, new Object[]{userName, password}, Integer.class);
        return result;
    }

    public User findUserByUserName(final String userName) {
        final User user = new User();
        String sqlStr = "select user_id, user_name from t_user where user_name=?";
        jdbcTemplate.query(sqlStr, new Object[]{userName}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(userName);
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        String sqlStr = "update t_user set last_visit=?, last_ip=? where user_id=?";
        jdbcTemplate.update(sqlStr, new Object[]{user.getLastVisit(), user.getLastIp(), user.getUserId()});
    }
}
