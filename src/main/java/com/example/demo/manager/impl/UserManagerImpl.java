package com.example.demo.manager.impl;

import com.example.demo.manager.UserManager;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        userMapper.list();
        return userMapper.list();
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getOne(id);
    }

}
