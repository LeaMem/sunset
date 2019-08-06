package com.lea.api.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {

//    @Bean(destroyMethod = "shutdown")
//    public RedissonClient redisson(@Value("${redission.url}") String url,
//                                   @Value("${redission.transportMode}") String transportMode) {
//        Config config = new Config();
//
//        config.useSingleServer()
//                .setAddress(url)
//                .setSslEnableEndpointIdentification(false)
//                .setClientName("HanClient");
//
//        config.setCodec(new JsonJacksonCodec());
//
//        config.setTransportMode(TransportMode.valueOf(transportMode));
//
//        return Redisson.create(config);
//    }


    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {

        Map<String, CacheConfig> config = new HashMap<>();

        return new RedissonSpringCacheManager(redissonClient, config);
    }

}
