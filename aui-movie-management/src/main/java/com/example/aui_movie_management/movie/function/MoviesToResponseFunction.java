package com.example.aui_movie_management.movie.function;

import com.example.aui_movie_management.movie.dto.GetMoviesResponse;
import com.example.aui_movie_management.movie.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class MoviesToResponseFunction implements Function<List<Movie>, GetMoviesResponse> {

    @Override
    public GetMoviesResponse apply(List<Movie> entities) {
        return GetMoviesResponse.builder()
                .movies(entities.stream()
                        .map(movie -> GetMoviesResponse.Movie.builder()
                                .id(movie.getId())
                                .name(movie.getName())
                                .date_of_release(movie.getDate_of_release())
                                .time(movie.getTime())
                                .genre(movie.getGenre())
                                .build())
                        .toList())
                .build();
    }
}
