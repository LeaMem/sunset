package com.lea.api.service;

import com.lea.api.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "user")
public interface UserService {

    @RequestMapping(value = "listAll")
    List<User> findAll();

}
