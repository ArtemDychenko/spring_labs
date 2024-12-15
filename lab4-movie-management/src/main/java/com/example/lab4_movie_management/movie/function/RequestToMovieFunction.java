package com.example.lab4_movie_management.movie.function;


import com.example.lab4_movie_management.director.entity.Director;
import com.example.lab4_movie_management.movie.dto.PutMovieRequest;
import com.example.lab4_movie_management.movie.entity.Movie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToMovieFunction implements BiFunction<UUID, PutMovieRequest, Movie> {

    @Override
    public Movie apply(UUID id, PutMovieRequest request) {
        return Movie.builder()
                .name(request.getName())
                .date_of_release(request.getDate_of_release())
                .time(request.getTime())
                .genre(request.getGenre())
                .director(Director.builder()
                        .build())
                .build();
    }
}
