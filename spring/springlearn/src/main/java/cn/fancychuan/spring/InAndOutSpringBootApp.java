package cn.fancychuan.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;


/**
 * 《深入浅出spring boot 2.x》跟spring相关的部分
 */
public class InAndOutSpringBootApp {
    private static Logger logger = Logger.getLogger("InAndOutSpringBootApp");

    public static void main(String[] args) {
        /**
         * AnnotationConfigApplicationContext基于注解的IoC容器，Spring Boot装配和获取bean的方法跟它如出一辙
         *
         * APPConfig传递给AnnotationConfigApplicationContext之后，就能将配置里面的Bean装配到IoC容器中
         */
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = ctx.getBean(User.class);
        logger.info(user.getNote() + "||" + user.getUserName());
    }
}

@Configuration // 表名这是一个Java配置文件，Spring会根据它来生成容器去装配Bean
class AppConfig {
    @Bean(name = "user")
    public User initUser() {
        User user = new User();
        user.setId(1L);
        user.setUserName("Spring");
        user.setNote("note_1");
        return user;
    }
}

class User {
    private Long id;
    private String userName;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}