package com.wingbank.gateway.config;

import com.wingbank.gateway.security.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final JwtAuthenticationFilter authFilter;

    public GatewayConfig(JwtAuthenticationFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Auth Service route - no JWT filter applied here
                .route("core", r -> r.path("/api/auth/**")
                        .uri("http://localhost:8081"))
                // Product Service route - apply JWT filter
                .route("acccount", r -> r.path("/api/accounts/**")
                        .filters(f -> f.filter(authFilter.apply(new JwtAuthenticationFilter.Config())))
                        .uri("http://localhost:8082"))
                .build();
    }
}
