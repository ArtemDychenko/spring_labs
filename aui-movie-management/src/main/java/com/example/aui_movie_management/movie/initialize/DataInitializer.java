package com.example.aui_movie_management.movie.initialize;


import com.example.aui_movie_management.director.entity.Director;
import com.example.aui_movie_management.director.service.impl.DirectorDefaultService;
import com.example.aui_movie_management.movie.entity.Movie;
import com.example.aui_movie_management.movie.service.impl.MovieDefaultService;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
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
                .name("Christopher Nolan")
                .yearOfBirth(1970)
                .photo(getResourceAsByteArray("/com/example/photo/nolan.png"))
                .build();

        Director yorgosLanthimos = Director.builder()
                .id(UUID.randomUUID())
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


        Movie inception =  Movie.builder()
                .id(UUID.randomUUID())
                .name("Inception")
                .dateOfRelease(2010)
                .time(148)
                .genre("Science/Fiction")
                .director(christopherNolan)
                .poster(getResourceAsByteArray("/com/example/poster/inception.png"))
                .build();

        Movie pulpFiction = Movie.builder()
                .id(UUID.randomUUID())
                .name("Pulp Fiction")
                .dateOfRelease(1994)
                .time(149)
                .genre("Crime/Thriller")
                .director(quentinTarantino)
                .poster(getResourceAsByteArray("/com/example/poster/pulpFiction.png"))
                .build();


        Movie killBill = Movie.builder()
                .id(UUID.randomUUID())
                .name("Kill Bill")
                .dateOfRelease(2004)
                .time(111)
                .genre("Action/Thriller")
                .director(quentinTarantino)
                .poster(getResourceAsByteArray("/com/example/poster/killBill.png"))
                .build();


        Movie theHandOfGod = Movie.builder()
                .id(UUID.randomUUID())
                .name("The Hand of God")
                .dateOfRelease(2021)
                .time(130)
                .genre("Comedy/Drama")
                .director(paoloSorrentino)
                .poster(getResourceAsByteArray("/com/example/poster/theHandOfGod.png"))
                .build();

        Movie theGreatBeaty = Movie.builder()
                .id(UUID.randomUUID())
                .name("The Great Beaty")
                .dateOfRelease(2013)
                .time(144)
                .genre("Comedy/Drama")
                .director(paoloSorrentino)
                .poster(getResourceAsByteArray("/com/example/poster/theGreatBeauty.png"))
                .build();

        Movie everythingEverywhereAllAtOnce = Movie.builder()
                .id(UUID.randomUUID())
                .name("Everything Everywhere All at Once")
                .dateOfRelease(2022)
                .time(140)
                .genre("Action/Adventure/Science Fiction")
                .director(danielKwan)
                .poster(getResourceAsByteArray("/com/example/poster/everythingEverywhere.png"))
                .build();

        Movie poorThings = Movie.builder()
                .id(UUID.randomUUID())
                .name("Poor Things")
                .dateOfRelease(2023)
                .time(142)
                .genre("Comedy/Romance/Science Fiction")
                .director(yorgosLanthimos)
                .poster(getResourceAsByteArray("/com/example/poster/poorThings.png"))
                .build();

        Movie kindOfKindness = Movie.builder()
                .id(UUID.randomUUID())
                .name("Kind of kindness")
                .dateOfRelease(2024)
                .time(188)
                .genre("Drama/Comedy")
                .director(yorgosLanthimos)
                .poster(getResourceAsByteArray("/com/example/poster/kindOfKindness.png"))
                .build();


        movieService.createMovie(inception);
        movieService.createMovie(killBill);
        movieService.createMovie(theHandOfGod);
        movieService.createMovie(pulpFiction);
        movieService.createMovie(theGreatBeaty);
        movieService.createMovie(everythingEverywhereAllAtOnce);
        movieService.createMovie(poorThings);
        movieService.createMovie(kindOfKindness);
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }
}
