package com.lea.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OAuth2TestController {

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "han")
    public Authentication home(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request.getSession());

        UsernamePasswordAuthenticationFilter bean = applicationContext.getBean(UsernamePasswordAuthenticationFilter.class);
        bean.attemptAuthentication(request, response);

        System.out.println(request.getSession());

        return SecurityContextHolder.getContext().getAuthentication();
    }


    @RequestMapping(value = "user")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());

        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

        return userInfo;


    }


}
