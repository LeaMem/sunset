package com.lea.api.fallback;

import com.lea.api.service.UserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserServiceFallback implements FallbackFactory<UserService> {


    @Override
    public UserService create(Throwable cause) {

        System.out.println(cause.getMessage());

        return ArrayList::new;
    }


}
