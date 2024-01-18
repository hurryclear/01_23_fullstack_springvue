package com.hurryclear.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data // provide getter&setter
public class User {
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;

}
