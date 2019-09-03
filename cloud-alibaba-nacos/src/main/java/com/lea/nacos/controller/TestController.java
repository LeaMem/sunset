package com.lea.nacos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @RequestMapping(value = "map")
    public Map<String, Object> test() {
        Map<String, Object> res = new HashMap<>();
        res.put("name", "sdf");
        res.put("age", 26);
        return res;
    }

}
