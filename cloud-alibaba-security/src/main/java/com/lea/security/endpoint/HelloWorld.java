package com.lea.security.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorld {


    @RequestMapping(value = "stu")
    public Map<String, Object> getStu(String name, Integer age){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);

        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("params", map);
        res.put("code", "dd");
        return res;
    }
}
