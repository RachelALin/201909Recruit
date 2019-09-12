package com.ctguqmx.recruit.service;

import com.ctguqmx.recruit.pojo.User;

import javax.security.auth.Subject;

public interface UserService {
    User login(String userName, String passWord);

    boolean register(User user);

    User findByUserName(String username);
}
