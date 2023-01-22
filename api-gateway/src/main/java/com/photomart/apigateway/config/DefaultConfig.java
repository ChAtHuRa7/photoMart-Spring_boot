package com.photomart.apigateway.config;

import com.photomart.apigateway.filters.AuthenticationPreFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DefaultConfig {

    @Value("${spring.gateway.excludedUrls}")
    private String excludedUrlsString;

    @Bean("excludedUrls")
    public List<String> excludedUrls(){
        return Arrays.stream(excludedUrlsString.split(",")).collect(Collectors.toList());
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, AuthenticationPreFilter authenticationPreFilter){
        return builder.routes()
                .route("auth-service-route",r -> r.path("/authentication-service/**")
                        .filters(f-> f.rewritePath("/authentication-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authenticationPreFilter.apply(new AuthenticationPreFilter.Config())))
                        .uri("lb://authentication-service"))


                .route("portfolio-service-route",r -> r.path("/portfolio-service/**")
                        .filters(f-> f.rewritePath("/portfolio-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authenticationPreFilter.apply(new AuthenticationPreFilter.Config())))
                        .uri("lb://portfolio-service"))

                .route("calendar-service-route",r -> r.path("/calendar-service/**")
                        .filters(f-> f.rewritePath("/calendar-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authenticationPreFilter.apply(new AuthenticationPreFilter.Config())))
                        .uri("lb://calendar-service"))

                .route("booking-service-route",r -> r.path("/booking-service/**")
                        .filters(f-> f.rewritePath("/booking-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authenticationPreFilter.apply(new AuthenticationPreFilter.Config())))
                        .uri("lb://booking-service"))

                .route("package-service-route",r -> r.path("/package-service/**")
                        .filters(f-> f.rewritePath("/package-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authenticationPreFilter.apply(new AuthenticationPreFilter.Config())))
                        .uri("lb://package-service"))

                .route("calendar-service-route",r -> r.path("/calendar-service/**")
                        .filters(f-> f.rewritePath("/calendar-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authenticationPreFilter.apply(new AuthenticationPreFilter.Config())))
                        .uri("lb://calendar-service"))

                .route("user-service-route",r -> r.path("/user-service/**")
                        .filters(f-> f.rewritePath("/user-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authenticationPreFilter.apply(new AuthenticationPreFilter.Config())))
                        .uri("lb://user-service"))

                .route("photographer-service-route",r -> r.path("/photographer-service/**")
                        .filters(f-> f.rewritePath("/photographer-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authenticationPreFilter.apply(new AuthenticationPreFilter.Config())))
                        .uri("lb://photographer-service"))

                .route("payment-service-route",r -> r.path("/payment-service/**")
                        .filters(f-> f.rewritePath("/payment-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authenticationPreFilter.apply(new AuthenticationPreFilter.Config())))
                        .uri("lb://payment-service"))

                .route("review-service-route",r -> r.path("/review-service/**")
                        .filters(f-> f.rewritePath("/review-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authenticationPreFilter.apply(new AuthenticationPreFilter.Config())))
                        .uri("lb://review-service"))

                .route("discovery-service-route",r -> r.path("/eureka/")
                        .filters(f-> f.rewritePath("/eureka(?<segment>/?.*)", "$\\{segment}"))
                        .uri("http://localhost:8761"))
                .route("discovery-service-static-route",r -> r.path("/eureka/**")
                        .uri("http://localhost:8761"))

                .build();

    }

}
