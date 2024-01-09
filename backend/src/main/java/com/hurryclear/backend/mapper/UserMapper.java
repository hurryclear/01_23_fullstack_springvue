package com.hurryclear.backend.mapper;

import com.hurryclear.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM sys_user")
    List<User> findALL();

}
