package com.fancychuan.service.impl;

import com.fancychuan.mapper.UserMapper;
import com.fancychuan.model.User;
import com.fancychuan.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServerImpl implements UserServer {
    @Autowired
    public UserMapper userMapper;


    @Override
    public User login(User user) {
        return userMapper.selectLogin(user);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }
}
