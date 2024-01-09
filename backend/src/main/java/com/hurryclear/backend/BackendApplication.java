package com.hurryclear.backend;

import com.hurryclear.backend.entity.User;
import com.hurryclear.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // note that this class is a 接口
@SpringBootApplication
public class BackendApplication {

    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @GetMapping("/") // dependent on @RestController
    public List<User> users() {
        return userMapper.findALL();
    }
}
