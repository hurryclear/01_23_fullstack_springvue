package com.hurryclear.backend.mapper;

import com.hurryclear.backend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("SELECT * FROM sys_user")
    List<User> findALL();

    // #{} change data to dynamic, it means I can add what I need later
    @Insert("INSERT INTO sys_user(username, password, nickname, email, phone, address) VALUES (#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address})")
//    INSERT INTO sys_user(username, password, nickname, email, phone, address) VALUES ("hurry", "122", "hoo", "hurry@gmail.com", "122132", "fsee");
    int insert(User user);

    // SQL for UPDATE here you update all data, in this case if you only change password
    // und you don't write other data then other data will be overwritten by null
    // what should you do for this? you need dynamic sql: User.xml
    // if you use dynamic sql then you need to delete the annotation @Update here
    // it is actually you define another "annotation" in User.xml
//    @Update("UPDATE sys_user SET username=#{username}, password=#{password}, nickname=#{nickname}, email=#{email}, phone=#{phone}, address=#{address} WHERE id = #{id}")
    int update(User user);
}
