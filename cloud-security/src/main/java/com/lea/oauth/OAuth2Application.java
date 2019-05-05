package com.lea.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lea")
public class OAuth2Application {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2Application.class, args);
    }

}
