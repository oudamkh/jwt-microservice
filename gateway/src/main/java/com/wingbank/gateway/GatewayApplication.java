package com.wingbank.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    // You can define routes in application.properties or programmatically.
    // Here's an example of programmatic routing (though we're using properties for simplicity in this example)
    /*
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("core", r -> r.path("/api/auth/**")
                        .uri("http://localhost:8081"))
                .route("acccount", r -> r.path("/api/accounts/**")
                        .uri("http://localhost:8082"))
                .build();
    }
    */
}
