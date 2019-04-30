package com.lea.consumer.config;

import com.lea.api.config.Avoid;
import com.lea.consumer.service.UserServiceImpl;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import feign.*;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Configuration
@Avoid
public class UserServiceConfig {

    @Bean
    public Feign.Builder customHystrixBuilder() {
        return HystrixFeign.builder().setterFactory(new SetterFactory() {
            @Override
            public HystrixCommand.Setter create(Target<?> target, Method method) {
                return HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(UserServiceImpl.class.getSimpleName()))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(200000)
                        );
            }
        });
    }


    @Bean
    public OkHttpClient okHttpClient() {

        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool())
                .build();
    }

    @Bean
    public IRule iRule() {
        return new BestAvailableRule();
    }


    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }

    @Bean
    public Retryer retryer(){
        return new Retryer.Default();
    }


}
