package com.hurryclear.backend.controller;

import com.hurryclear.backend.entity.User;
import com.hurryclear.backend.mapper.UserMapper;
import com.hurryclear.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // note that this class is a 接口, is a combined annotation (@Controller + @ResponseBody)
@RequestMapping("/user") // suffix for url localhost:9090/user
public class UserController {


    @Autowired
    private UserMapper userMapper;
    // insert mapping (insert new user in database)

    @Autowired
    private UserService userService;


    // save: insert(new) and update(old)
    @PostMapping //前后端的接口
    // save: add or update. How can I use one method 'sava' to add user or update user? UserService
    // the return data is Integer, what does that mean?
    public Integer save(@RequestBody User user) { // what is @RequestBody? instance of json from frontend change to instance of java in backend
//        return userMapper.insert(user);
        return userService.save(user); // use userService.save to insert(add) or update user
    }


    // find all data
    // get mapping (get user in database)
    @GetMapping // localhost:9090, if here have /, then at then end of url also need /
    public List<User> index() {
        return userMapper.findALL();
    }

    //delete
    @DeleteMapping("/{id}") // this id must be same with variable id below
    public Integer delete(@PathVariable Integer id) { //@PathVariable:
        return userMapper.deleteById(id);
    }

    // find users and show in pages
    // /user/page?pageNum=1&pageSize=10
    // pageNum and pageSize in url will be passed to parameter below
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username) {

        pageNum = (pageNum - 1) * pageSize; // because of difference counting from database

        List<User> data = userMapper.selectPage(pageNum, pageSize, username); // get certain numbers of data
        Integer total = userMapper.selectTotal(username); // count total number of data

        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);

        return res;

    }
}
