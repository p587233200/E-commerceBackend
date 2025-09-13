package com.cheng.ecommercebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;
    private String avatar;
    private String name;
    private String password;
    private String desc;
    private List<String> roles;
    private List<String> buttons;
    private List<String> routes;
    private String token;
}
