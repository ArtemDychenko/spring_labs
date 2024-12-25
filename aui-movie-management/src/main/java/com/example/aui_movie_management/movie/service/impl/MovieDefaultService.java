package com.example.aui_movie_management.movie.service.impl;

import com.example.aui_movie_management.movie.entity.Movie;
import com.example.aui_movie_management.director.repository.api.DirectorRepository;
import com.example.aui_movie_management.movie.repository.api.MovieRepository;
import com.example.aui_movie_management.movie.service.api.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieDefaultService implements MovieService {
    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;


    @Autowired
    public MovieDefaultService(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;

    }


    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
//
//    public List<Movie> getMoviesByGenre(String genre) {
//        return movieRepository.findByGenre(genre);
//    }
//
//
    @Override
    public void createMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> getMovieById(UUID id) {
        return movieRepository.findById(id);
    }

    @Override
    public void deleteMovie(UUID id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<List<Movie>> findAllByDirector(UUID directorId) {
        return directorRepository.findById(directorId)
                .map(movieRepository::findAllByDirector);
    }

    @Override
    public Optional<byte[]> findPoster(UUID id) {
        return movieRepository.findPosterById(id);
    }

    @Override
    public void updatePoster(UUID id, InputStream is) {
        movieRepository.findById(id).ifPresent(movie -> {
            try {
                movie.setPoster(is.readAllBytes());
                movieRepository.save(movie);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }


}
