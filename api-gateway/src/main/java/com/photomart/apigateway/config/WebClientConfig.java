package com.photomart.apigateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Consumer;

@Configuration
public class WebClientConfig {


    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){

        return WebClient.builder();

    }
}
