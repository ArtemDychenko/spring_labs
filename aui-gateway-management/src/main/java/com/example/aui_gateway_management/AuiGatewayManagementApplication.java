package com.example.aui_gateway_management;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuiGatewayManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuiGatewayManagementApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            @Value("${rpg.movie.url}") String movieURL,
            @Value("${rpg.director.url}") String directorURL,
            @Value("${rpg.gatewy.host}") String host
    ) {
        return builder
                .routes()
                .route("directors", route -> route
                        .host(host)
                        .and()
                        .path(
                                "/api/directors/{uuid}",
                                "/api/directors"
                        )
                        .uri(directorURL)
                )
                .route("movies", route -> route
                        .host(host)
                        .and()
                        .path(
                                "/api/movies",
                                "/api/movies/**",
                                "/api/directors/{uuid}/movies",
                                "/api/directors/{uuid}/movies/**"
                        )
                        .uri(movieURL)
                )
                .build();
    }
}
