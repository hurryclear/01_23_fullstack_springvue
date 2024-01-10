package com.hurryclear.backend.controller;

import com.hurryclear.backend.entity.User;
import com.hurryclear.backend.mapper.UserMapper;
import com.hurryclear.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // note that this class is a 接口, is a combined annotation (@Controller + @ResponseBody)
@RequestMapping("/user") // suffix for url localhost:9090/user
public class UserController {


    @Autowired
    private UserMapper userMapper;
    // insert mapping (insert new user in database)

    @Autowired
    private UserService userService;


    @PostMapping //前后端的接口
    // save: add or update. How can I use one method 'sava' to add user or update user? UserService
    // the return data is Integer, what does that mean?
    public Integer save(@RequestBody User user) { // what is @RequestBody? instance of json from frontend change to instance of java in backend
//        return userMapper.insert(user);
        return userService.save(user); // use userService.save to insert(add) or update user
    }


    // get mapping (get user in database)
    @GetMapping // localhost:9090, if here have /, then at then end of url also need /
    public List<User> index() {
        return userMapper.findALL();
    }
}
