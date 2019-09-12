package com.ctguqmx.recruit.service.Impl;

import com.ctguqmx.recruit.dao.UserMapper;
import com.ctguqmx.recruit.pojo.User;
import com.ctguqmx.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userName, String passWord) {
        return userMapper.selectUser(userName,passWord);
    }

    @Override
    public boolean register(User user) {
        userMapper.insertUser(user);
        return true;
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.selectByUserName(username);
    }
}
