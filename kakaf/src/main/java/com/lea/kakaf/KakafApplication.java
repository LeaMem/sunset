package com.lea.kakaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lea")
public class KakafApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakafApplication.class, args);
    }

}
