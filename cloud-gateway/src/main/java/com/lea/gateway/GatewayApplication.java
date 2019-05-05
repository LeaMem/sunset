package com.lea.gateway;

import com.lea.gateway.filter.CustomGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.function.Function;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.lea")
//@EnableResourceServer
//@EnableAuthorizationServer

public class GatewayApplication {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route(new Function<PredicateSpec, Route.AsyncBuilder>() {
                    @Override
                    public Route.AsyncBuilder apply(PredicateSpec predicateSpec) {

                        return predicateSpec.path("/consumer/**")
                                .filters(new Function<GatewayFilterSpec, UriSpec>() {
                                    @Override
                                    public UriSpec apply(GatewayFilterSpec f) {
                                        return f.filter(new CustomGatewayFilter())
                                                .preserveHostHeader();
                                    }
                                })
                                .uri("lb://han-consumer");
                    }
                }).build();

    }

//    @Bean
//    public WebFilter contextPathWebFilter() {
//        String contextPath = "gateway";
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            if (request.getURI().getPath().startsWith(contextPath)) {
//                return chain.filter(
//                        exchange.mutate()
//                                .request(request.mutate().contextPath(contextPath).build())
//                                .build());
//            }
//            return chain.filter(exchange);
//        };
//    }


    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);

    }

}
