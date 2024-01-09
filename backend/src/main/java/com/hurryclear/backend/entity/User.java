package com.hurryclear.backend.entity;

import javax.swing.*;
import lombok.Data;

@Data // provide getter&setter
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Spring address;
}
