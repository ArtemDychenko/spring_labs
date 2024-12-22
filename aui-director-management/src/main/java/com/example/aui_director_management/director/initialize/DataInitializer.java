package com.example.aui_director_management.director.initialize;

import com.example.aui_director_management.director.entity.Director;
import com.example.aui_director_management.director.service.impl.DirectorDefaultService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class DataInitializer {

    private final DirectorDefaultService directorService;


    @Autowired
    public DataInitializer(DirectorDefaultService directorService) {
        this.directorService = directorService;

    }


    @PostConstruct
    public void init() {

        Director christopherNolan = Director.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76"))
                .name("Christopher Nolan")
                .yearOfBirth(1970)
                .build();

        Director yorgosLanthimos = Director.builder()
                .id(UUID.randomUUID())
                .name("Yorgos Lanthimos")
                .yearOfBirth(1973)
                .build();

        Director paoloSorrentino = Director.builder()
                .id(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd"))
                .name("Paolo Sorrentino")
                .yearOfBirth(1970)
                .build();

        Director quentinTarantino = Director.builder()
                .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4"))
                .name("Quentin Tarantino")
                .yearOfBirth(1963)
                .build();


        directorService.createDirector(christopherNolan);
        directorService.createDirector(yorgosLanthimos);
        directorService.createDirector(paoloSorrentino);
        directorService.createDirector(quentinTarantino);
    }
}
