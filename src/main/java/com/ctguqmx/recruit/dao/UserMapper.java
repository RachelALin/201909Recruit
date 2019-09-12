package com.ctguqmx.recruit.dao;

import com.ctguqmx.recruit.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User selectById(Integer id);
    User selectUser(String username,String password);
    void insertUser(User user);
    User selectByUserName(String username);
}
