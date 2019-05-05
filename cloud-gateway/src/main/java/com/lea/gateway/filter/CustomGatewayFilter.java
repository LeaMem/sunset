package com.lea.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

public class CustomGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Map<String, Object> attributes = exchange.getAttributes();
        attributes.forEach((s, o) -> System.out.println("key is: " + s + "  \t value is : " + o));

        exchange.getAttributes().put("countStartTime", System.currentTimeMillis());

        return chain.filter(exchange)
                .then(
                        Mono.fromRunnable(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Long startTime = exchange.getAttribute("countStartTime");
                                        Long endTime = (System.currentTimeMillis() - startTime);
                                        if (startTime != null) {
                                            System.out.println(exchange.getRequest().getURI().getRawPath() + ": " + endTime + "ms");
                                        }
                                    }
                                }
                        )
                );
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
