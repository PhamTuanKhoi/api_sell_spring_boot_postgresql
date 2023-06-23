package com.sell.tea.controllers;

import com.sell.tea.entities.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping
    public String findAll(){
        return "hello admin";
    }

    @GetMapping("/role")
    public String role(){
        return "hello user";
    }
}
