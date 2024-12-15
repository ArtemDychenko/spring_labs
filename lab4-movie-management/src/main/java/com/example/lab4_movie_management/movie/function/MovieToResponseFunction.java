package com.example.lab4_movie_management.movie.function;

//import com.example.lab4_movie_management.director.dto.GetDirectorResponse;
import com.example.lab4_movie_management.movie.dto.GetMovieResponse;
import com.example.lab4_movie_management.movie.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MovieToResponseFunction implements Function<Movie, GetMovieResponse> {

    @Override
    public GetMovieResponse apply(Movie entity) {
        return GetMovieResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .date_of_release(entity.getDate_of_release())
                .time(entity.getTime())
                .genre(entity.getGenre())
                .director(GetMovieResponse.Director.builder()
                        .id(entity.getDirector().getId())
                        .build())
                .build();
    }

}
