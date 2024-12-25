package com.example.aui_movie_management.movie.function;

//import com.example.aui_movie_management.director.dto.GetDirectorResponse;
import com.example.aui_movie_management.movie.dto.GetMovieResponse;
import com.example.aui_movie_management.movie.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MovieToResponseFunction implements Function<Movie, GetMovieResponse> {

    @Override
    public GetMovieResponse apply(Movie entity) {
        return GetMovieResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .dateOfRelease(entity.getDateOfRelease())
                .time(entity.getTime())
                .genre(entity.getGenre())
                .director(GetMovieResponse.Director.builder()
                        .id(entity.getDirector().getId())
                        .name(entity.getDirector().getName())
                        .build())
                .build();
    }

}
