package com.fancychuan.mybatis;

import com.fancychuan.mybatis.mapper.UserMapper;
import com.fancychuan.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
    private static SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) {
        String resource = "mybatis.cfg.xml";

        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // insertUser();
        // updateUser();
        // deleteUser();
        // selectUserById();
        selectAllUser();
    }

    private static void insertUser() {
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("fancy2");
        user.setPassword("123456");
        user.setSex("女");
        user.setAddress("汕头");
        try {
            mapper.insertUser(user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

        session.close();
    }

    private static void updateUser() {
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = null;
        try {
            user = mapper.selectUserById(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setAddress("成都");
        try {
            mapper.updateUser(user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        session.close();
    }

    private static void deleteUser() {
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            mapper.deleteUser(2);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        session.close();
    }

    private static void selectUserById() {

        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            User user = mapper.selectUserById(1);
            // session.commit(); // todo: 似乎这里加不加commit都可以
            System.out.println(user.getId() + " " + user.getUsername() + " "
                    + user.getPassword() + " " + user.getSex() + " "
                    + user.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

        session.close();
    }

    private static void selectAllUser() {
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        try {
            List<User> userList = mapper.selectAllUser();
            session.commit();
            for (User user: userList) {
                System.out.println(user.getId() + " " + user.getUsername() + " "
                        + user.getPassword() + " " + user.getSex() + " "
                        + user.getAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        session.close();
    }

}
