package com.example.aui_director_management.movie.service.api;

import com.example.aui_director_management.movie.entity.Movie;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {

    Optional<Movie> getMovieById(UUID id);

    void createMovie(Movie movie);

    void deleteMovie(UUID id);

    Optional<byte[]> findPoster(UUID id);

    void updatePoster(UUID id, InputStream is);
}
