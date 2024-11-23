package com.example.aui.config;

import com.example.aui.Director;
import com.example.aui.Movie;
import com.example.aui.service.MovieService;
import com.example.aui.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final MovieService movieService;
    private final DirectorService directorService;

    @Bean
    @Primary
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setPort(8082); // Ustaw port na 8081 lub inny
        return tomcat;
    }

    @Autowired
    public DataInitializer(MovieService movieService, DirectorService directorService) {
        this.movieService = movieService;
        this.directorService = directorService;
    }

    @PostConstruct
    public void init() {
        // Tworzenie reżyserów
        Director christopherNolan = new Director("Christopher Nolan", 1970);
        Director yorgosLanthimos = new Director("Yorgos Lanthimos", 1973);
        Director paoloSorrentino = new Director("Paolo Sorrentino", 1970);
        Director quentinTarantino = new Director("Quentin Tarantino", 1963);

        directorService.saveDirector(christopherNolan);
        directorService.saveDirector(yorgosLanthimos);
        directorService.saveDirector(paoloSorrentino);
        directorService.saveDirector(quentinTarantino);

        // Tworzenie filmów z użyciem wzorca Builder
        Movie inception = new Movie.Builder("Inception", 2010)
                .time(148)
                .genre("Science Fiction")
                .setDirector(christopherNolan)
                .build();
        christopherNolan.addMovie(inception);

        Movie pulpFiction = new Movie.Builder("Pulp Fiction", 1994)
                .time(149)
                .genre("Crime Thriller")
                .setDirector(quentinTarantino)
                .build();
        quentinTarantino.addMovie(pulpFiction);

        Movie killBill = new Movie.Builder("Kill Bill", 2004)
                .time(111)
                .genre("Action Thriller")
                .setDirector(quentinTarantino)
                .build();
        quentinTarantino.addMovie(killBill);

        Movie theHandOfGod = new Movie.Builder("The Hand of God", 2021)
                .time(130)
                .genre("Comedy Drama")
                .setDirector(paoloSorrentino)
                .build();
        paoloSorrentino.addMovie(theHandOfGod);

        // Zapisanie filmów w bazie danych
        movieService.saveMovie(inception);
        movieService.saveMovie(killBill);
        movieService.saveMovie(theHandOfGod);
        movieService.saveMovie(pulpFiction);
    }
}
