package com.example.lab3_management.Object.service.api;

import com.example.lab3_management.Object.entity.Director;
import com.example.lab3_management.Object.entity.Movie;

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
