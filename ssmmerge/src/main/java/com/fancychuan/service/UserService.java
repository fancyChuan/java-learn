package com.fancychuan.service;

import com.fancychuan.model.User;

import java.util.List;

public interface UserService {
    User login(User user);
    List<User> selectAllUser();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
}
