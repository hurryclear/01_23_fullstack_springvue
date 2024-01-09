package com.hurryclear.backend.controller;

import com.hurryclear.backend.entity.User;
import com.hurryclear.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // note that this class is a 接口, is a combined annotation (@Controller + @ResponseBody)
@RequestMapping("/user") // suffix for url localhost:9090/user
public class UserController {

    @Autowired
    private UserMapper userMapper;


    // find user in database
    @GetMapping("/") // localhost:9090, if here have /, then at then end of url also need /
    public List<User> user() {
        return userMapper.findALL();
    }
}
