package com.photomart.apigateway.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;


public class AuthorizationFilter implements GlobalFilter {

    @Autowired
    @Qualifier("excludedUrls")
    private List<String> excludeUrls;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        System.out.println("##########    global filter    ##########");

        return chain.filter(exchange);
    }


    public Predicate<ServerHttpRequest> isSecured = request -> excludeUrls.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
}
