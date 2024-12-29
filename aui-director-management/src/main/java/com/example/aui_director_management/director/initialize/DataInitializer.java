package com.example.aui_director_management.director.initialize;

import com.example.aui_director_management.director.entity.Director;
import com.example.aui_director_management.director.service.impl.DirectorDefaultService;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
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
                .photo(getResourceAsByteArray("/com/example/photo/nolan.png"))
                .build();

        Director yorgosLanthimos = Director.builder()
                .id(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a612345"))
                .name("Yorgos Lanthimos")
                .yearOfBirth(1973)
                .photo(getResourceAsByteArray("/com/example/photo/lathimos.png"))
                .build();

        Director paoloSorrentino = Director.builder()
                .id(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd"))
                .name("Paolo Sorrentino")
                .yearOfBirth(1970)
                .photo(getResourceAsByteArray("/com/example/photo/sorrentino.png"))
                .build();

        Director quentinTarantino = Director.builder()
                .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4"))
                .name("Quentin Tarantino")
                .yearOfBirth(1963)
                .photo(getResourceAsByteArray("/com/example/photo/tarantino.png"))
                .build();

        Director danielKwan = Director.builder()
                .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d11786712345"))
                .name("Daniel Kwan")
                .yearOfBirth(1988)
                .photo(getResourceAsByteArray("/com/example/photo/kwan.png"))
                .build();


        directorService.createDirector(christopherNolan);
        directorService.createDirector(yorgosLanthimos);
        directorService.createDirector(paoloSorrentino);
        directorService.createDirector(quentinTarantino);
        directorService.createDirector(danielKwan);
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }
}
