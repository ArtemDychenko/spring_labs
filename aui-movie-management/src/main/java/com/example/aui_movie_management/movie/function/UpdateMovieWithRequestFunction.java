package com.example.aui_movie_management.movie.function;

import com.example.aui_movie_management.movie.dto.PatchMovieRequest;
import com.example.aui_movie_management.movie.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UpdateMovieWithRequestFunction implements BiFunction<Movie, PatchMovieRequest, Movie> {

    @Override
    public Movie apply(Movie entity, PatchMovieRequest request) {
        return Movie.builder()
                .name(request.getName())
                .dateOfRelease(request.getDateOfRelease())
                .time(request.getTime())
                .genre(request.getGenre())
                .director(entity.getDirector())
                .poster(entity.getPoster())
                .build();
    }
}
