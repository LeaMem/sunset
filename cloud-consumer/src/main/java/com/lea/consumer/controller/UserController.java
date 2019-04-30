package com.lea.consumer.controller;

import com.lea.api.entity.User;
import com.lea.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "findAll")
    public List<User> findAll() {

        return userService.findAll();
    }

}
