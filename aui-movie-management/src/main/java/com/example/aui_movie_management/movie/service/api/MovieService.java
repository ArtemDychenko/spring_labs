package com.example.aui_movie_management.movie.service.api;

import com.example.aui_movie_management.movie.entity.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {

    Optional<Movie> getMovieById(UUID id);

    void createMovie(Movie movie);

    List<Movie> getAllMovies();

    void updateMovie(Movie movie);

    void deleteMovie(UUID id);

    Optional<List<Movie>> findAllByDirector(UUID directorId);

}
