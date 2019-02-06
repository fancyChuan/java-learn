package com.fancychuan.mapper;

import com.fancychuan.model.User;

import java.util.List;

public interface UserMapper {
    User selectLogin(User user);

    List<User> selectAllUser();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);
}
