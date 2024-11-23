package com.example.aui.service;

import com.example.aui.Director;
import com.example.aui.Movie;
import com.example.aui.repository.MovieRepository;
import com.example.aui.repository.DirectorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.UUID;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final DirectorService directorService;

    @Autowired
    public MovieService(MovieRepository movieRepository, DirectorService directorService) {
        this.movieRepository = movieRepository;
        this.directorService = directorService;
    }

    @Transactional
    public void printAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        movies.forEach(System.out::println);
    }

    @Transactional
    public Movie createMovie(String directorName, String name, int dateOfRelease, int time, String genre) {
        Director director = directorService.getAllDirectors().stream()
                .filter(d -> d.getName().equalsIgnoreCase(directorName))
                .findFirst()
                .orElseGet(() -> directorService.saveDirector(new Director(directorName, 0)));

        Movie movie = new Movie.Builder(name, dateOfRelease)
                .time(time)
                .genre(genre)
                .setDirector(director)
                .build();
        director.addMovie(movie);

        return movie;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }


    public Movie saveMovie(Movie movie) {
        if (movie.getDirector() != null) {
            UUID directorId = movie.getDirector().getId();
            if (directorId == null || directorService.getDirectorById(directorId) == null) {
                throw new IllegalArgumentException("Director with ID " + directorId + " does not exist.");
            }
        }
        return movieRepository.save(movie);
    }


    public Movie getMovieById(UUID id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteMovieByName(String name) {
        Movie movieToDelete = movieRepository.findAll().stream()
                .filter(movie -> movie.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Movie with name " + name + " does not exist."));

        // Usuwanie filmu z listy reżysera
        Director director = movieToDelete.getDirector();
        if (director != null) {
            director.getMovies().remove(movieToDelete);
        }

        // Następnie usuń film z bazy danych
        movieRepository.delete(movieToDelete);
    }

    public void deleteMovie(UUID id) {
        movieRepository.deleteById(id);
    }

}
