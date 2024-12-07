package com.example.lab3_management.Object.initialize;

import com.example.lab3_management.Object.entity.Director;

import com.example.lab3_management.Object.entity.Movie;
import com.example.lab3_management.Object.service.impl.DirectorDefaultService;
import com.example.lab3_management.Object.service.impl.MovieDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataInitializer {

    private final DirectorDefaultService directorService;
    private final MovieDefaultService movieService;

    @Bean
    @Primary
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setPort(8086);
        return tomcat;
    }

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
                .name("Christopher Nolan")
                .yearOfBirth(1970)
                .build();

        Director yorgosLanthimos = Director.builder()
                .name("Yorgos Lanthimos")
                .yearOfBirth(1973)
                .build();

        Director paoloSorrentino = Director.builder()
                .name("Paolo Sorrentino")
                .yearOfBirth(1970)
                .build();

        Director quentinTarantino = Director.builder()
                .name("Quentin Tarantino")
                .yearOfBirth(1963)
                .build();


        directorService.createDirector(christopherNolan);
        directorService.createDirector(yorgosLanthimos);
        directorService.createDirector(paoloSorrentino);
        directorService.createDirector(quentinTarantino);


        Movie inception =  Movie.builder()
                .name("Inception")
                .date_of_release(2010)
                .time(148)
                .genre("Science Fiction")
                .director(christopherNolan)
                .build();

        Movie pulpFiction = Movie.builder()
                .name("Pulp Fiction")
                .date_of_release(1994)
                .time(149)
                .genre("Crime Thriller")
                .director(quentinTarantino)
                .build();


        Movie killBill = Movie.builder()
                .name("Kill Bill")
                .date_of_release(2004)
                .time(111)
                .genre("Action Thriller")
                .director(quentinTarantino)
                .build();


        Movie theHandOfGod = Movie.builder()
                .name("The Hand of God")
                .date_of_release(2021)
                .time(130)
                .genre("Comedy Drama")
                .director(paoloSorrentino)
                .build();

        movieService.createMovie(inception);
        movieService.createMovie(killBill);
        movieService.createMovie(theHandOfGod);
        movieService.createMovie(pulpFiction);

    }
}
