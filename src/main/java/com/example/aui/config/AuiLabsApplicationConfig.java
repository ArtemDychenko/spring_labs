package com.example.aui.config;

import com.example.aui.Movie;
import com.example.aui.Director;
import com.example.aui.service.DirectorService;
import com.example.aui.service.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AuiLabsApplicationConfig {

    @Bean
    public CommandLineRunner demo(MovieService movieService, DirectorService directorService) {
        return (args) -> {

        };
    }

    @Bean
    @Primary
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setPort(8081); // Ustaw port na 8081 lub inny
        return tomcat;
    }
}
