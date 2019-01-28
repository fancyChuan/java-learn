package com.fancychuan.mybatis.mapper;

import com.fancychuan.mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapperAnnotation {

    @Insert("insert into user(name, sex, age) values(#{name},#{sex},#{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertUser(User user) throws Exception;

    @Update("update user set age=#{age} where id=#{id}")
    public void updateUser(User user) throws Exception;

    @Delete("delete from user where id=#{user_id}")
    public int deleteUser(@Param("user_id") Integer id) throws Exception;

    @Select("select * from user where id=#{id}")
    @Results({
            @Result(id=true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "age", column = "age"),
    })
    public User selectUserById(Integer id) throws Exception;

    @Select("select * from user")
    public List<User> selectAllUser() throws Exception;
}
