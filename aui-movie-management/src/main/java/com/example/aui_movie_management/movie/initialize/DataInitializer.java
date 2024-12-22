package com.example.aui_movie_management.movie.initialize;


import com.example.aui_movie_management.director.entity.Director;
import com.example.aui_movie_management.director.service.impl.DirectorDefaultService;
import com.example.aui_movie_management.movie.entity.Movie;
import com.example.aui_movie_management.movie.service.impl.MovieDefaultService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class DataInitializer {

    private final DirectorDefaultService directorService;
    private final MovieDefaultService movieService;



    @Autowired
    public DataInitializer(DirectorDefaultService directorService, MovieDefaultService movieService) {
        this.directorService = directorService;
        this.movieService = movieService;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostConstruct
    public void init() {

        Director christopherNolan = Director.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76"))
                .build();

        Director yorgosLanthimos = Director.builder()
                .id(UUID.randomUUID())
                .build();

        Director paoloSorrentino = Director.builder()
                .id(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd"))
                .build();

        Director quentinTarantino = Director.builder()
                .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4"))
                .build();


        directorService.createDirector(christopherNolan);
        directorService.createDirector(yorgosLanthimos);
        directorService.createDirector(paoloSorrentino);
        directorService.createDirector(quentinTarantino);


        Movie inception =  Movie.builder()
                .id(UUID.randomUUID())
                .name("Inception")
                .date_of_release(2010)
                .time(148)
                .genre("Science/Fiction")
                .director(christopherNolan)
                .build();

        Movie pulpFiction = Movie.builder()
                .id(UUID.randomUUID())
                .name("Pulp Fiction")
                .date_of_release(1994)
                .time(149)
                .genre("Crime/Thriller")
                .director(quentinTarantino)
                .build();


        Movie killBill = Movie.builder()
                .id(UUID.randomUUID())
                .name("Kill Bill")
                .date_of_release(2004)
                .time(111)
                .genre("Action/Thriller")
                .director(quentinTarantino)
                .build();


        Movie theHandOfGod = Movie.builder()
                .id(UUID.randomUUID())
                .name("The Hand of God")
                .date_of_release(2021)
                .time(130)
                .genre("Comedy/Drama")
                .director(paoloSorrentino)
                .build();

        Movie theGreatBeaty = Movie.builder()
                .id(UUID.randomUUID())
                .name("The Great Beaty")
                .date_of_release(2013)
                .time(144)
                .genre("Comedy/Drama")
                .director(paoloSorrentino)
                .build();


        movieService.createMovie(inception);
        movieService.createMovie(killBill);
        movieService.createMovie(theHandOfGod);
        movieService.createMovie(pulpFiction);
        movieService.createMovie(theGreatBeaty);
    }
}
