package com.lea.consumer.service;

import com.lea.api.fallback.UserServiceFallback;
import com.lea.api.service.UserService;
import com.lea.consumer.config.UserServiceConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${service.provider}", path = "provider", fallbackFactory = UserServiceFallback.class, configuration = UserServiceConfig.class)
public interface UserServiceImpl extends UserService {
}
