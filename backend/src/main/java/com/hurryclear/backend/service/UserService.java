package com.hurryclear.backend.service;

import com.hurryclear.backend.entity.User;
import com.hurryclear.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int save(User user) {

        // if no id then insert(add new user), else update
        if(user.getId() == null) {
            return userMapper.insert(user);
        } else {
            return userMapper.update(user);
        }
    }
}
