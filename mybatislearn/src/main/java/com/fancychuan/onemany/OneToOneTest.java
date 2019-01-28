package com.fancychuan.onemany;

import com.fancychuan.onemany.mapper.ClassesMapper;
import com.fancychuan.onemany.model.Classes;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;

public class OneToOneTest {
    private static SqlSessionFactory sqlSessionFactory;

    public static void main(String[] ages) {
        String resouce = "mybatis.cfg.xml";

        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resouce);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        ClassesMapper mapper = session.getMapper(ClassesMapper.class);

        try {
            Classes classes = mapper.selectClassById(1);
            session.commit();
            System.out.println(classes.getId() + "," + classes.getName() + ",["
                    + classes.getTeacher().getId() + ","
                    + classes.getTeacher().getName() + ","
                    + classes.getTeacher().getAge()+"]");
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

        session.close();

    }
}
