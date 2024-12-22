package com.example.aui_movie_management.movie.function;

import com.example.aui_movie_management.movie.dto.PatchMovieRequest;
import com.example.aui_movie_management.movie.entity.Movie;

import java.util.function.BiFunction;

public class UpdateMovieWithRequestFunction implements BiFunction<Movie, PatchMovieRequest, Movie> {

    @Override
    public Movie apply(Movie entity, PatchMovieRequest request) {
        return Movie.builder()
                .name(request.getName())
                .date_of_release(request.getDate_of_release())
                .time(request.getTime())
                .genre(request.getGenre())
                .director(entity.getDirector())
                .build();
    }
}
