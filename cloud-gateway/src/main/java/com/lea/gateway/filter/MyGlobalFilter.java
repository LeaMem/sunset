package com.lea.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Profiles;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Component
@Order
public class MyGlobalFilter implements GlobalFilter {


    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MetricsEndpoint metricsEndpoint;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        HttpHeaders headers = request.getHeaders();

        String token = headers.getFirst("token");

//        if (StringUtils.isEmpty(token)) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().allocateBuffer();


        boolean pro = applicationContext.getEnvironment().acceptsProfiles(Profiles.of("pro", "!dev"));

        System.out.println(pro);

        MetricsEndpoint.ListNamesResponse listNamesResponse = metricsEndpoint.listNames();

        List<MetricsEndpoint.Sample> measurements = metricsEndpoint
                .metric("jvm.memory.max", null)
                .getMeasurements();

        measurements.forEach(sample -> System.out.println(sample.getValue()));

        Set<String> names = listNamesResponse.getNames();

        for (String name : names) {
            System.out.println(name);
        }


        return chain.filter(exchange);
    }
}
