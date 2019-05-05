package com.lea.oauth.controller;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OAuth2TestController {

    @RequestMapping(value = "h")
    public String home() {
        return "hello world";
    }


    @RequestMapping(value = "user")
    public Map<String, Object> user(OAuth2Authentication user){
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());

        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

        return userInfo;



    }

}
