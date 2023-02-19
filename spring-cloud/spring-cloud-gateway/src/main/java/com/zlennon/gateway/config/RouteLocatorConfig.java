package com.zlennon.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/test")
                        .uri("https://www.zlennon.com"))
                .route("chatgpt", r -> r.path("/chatgpt/**")
                        .uri("lb://chatgpt-service"))
                .build();
    }
}
