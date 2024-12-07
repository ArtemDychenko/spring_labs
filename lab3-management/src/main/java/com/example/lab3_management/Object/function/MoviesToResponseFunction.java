package com.example.lab3_management.Object.function;

import com.example.lab3_management.Object.dto.GetDirectorsResponse;
import com.example.lab3_management.Object.dto.GetMovieResponse;
import com.example.lab3_management.Object.dto.GetMoviesResponse;
import com.example.lab3_management.Object.entity.Director;
import com.example.lab3_management.Object.entity.Movie;
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
