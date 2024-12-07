package com.example.lab3_management.Object.service.impl;

import com.example.lab3_management.Object.entity.Movie;
import com.example.lab3_management.Object.repository.DirectorRepository;
import com.example.lab3_management.Object.repository.MovieRepository;
import com.example.lab3_management.Object.service.api.MovieService;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieDefaultService implements MovieService {
    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    private final String directorServiceUrl = "http://localhost:8082/api/directors";

    @Autowired
    public MovieDefaultService(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;

    }

//    @Transactional
//    public void printAllMovies() {
//        List<Movie> movies = movieRepository.findAll();
//        movies.forEach(System.out::println);
//    }

//    @Transactional
//    public Movie createMovie(String directorName, String name, int dateOfRelease, int time, String genre) {
//        Director director = findDirectorByName(directorName);
//
//        if (director == null) {
//            director = createDirector(new Director(directorName, 0));
//        }
//
//        Movie movie = new Movie.Builder(name, dateOfRelease)
//                .time(time)
//                .genre(genre)
//                .setDirector(director)
//                .build();
//        director.addMovie(movie);
//
//        return movieRepository.save(movie);
//    }
//
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
//
//
    @Override
    public Optional<Movie> getMovieById(UUID id) {
        return movieRepository.findById(id);
    }
//
//    @Transactional
//    public void deleteMovieByName(String name) {
//        Movie movieToDelete = movieRepository.findAll().stream()
//                .filter(movie -> movie.getName().equalsIgnoreCase(name))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Movie with name " + name + " does not exist."));
//
//        Director director = movieToDelete.getDirector();
//        if (director != null) {
//            director.getMovies().remove(movieToDelete);
//        }
//
//        movieRepository.delete(movieToDelete);
//    }
//
    @Override
    public void deleteMovie(UUID id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<List<Movie>> findAllByDirector(UUID directorId) {
        return directorRepository.findById(directorId)
                .map(movieRepository::findAllByDirector);
    }

    //================
    // Metody pomocnicze
    //============

//    public List<Director> deserializeDirectors(String json) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return objectMapper.readValue(json, new TypeReference<List<Director>>() {});
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error during JSON deserialization", e);
//        }
//    }
//
//    private Director findDirectorByName(String name) {
//        List<Director> directors = restTemplate.exchange(
//                "http://example.com/api/directors?name=" + name,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Director>>() {}
//        ).getBody();
//
//        return directors.stream()
//                .filter(director -> director.getName().equals(name))
//                .findFirst()
//                .orElse(null);
//    }
//
//    private Director createDirector(Director director) {
//        String url = String.format("%s", directorServiceUrl);
//        return restTemplate.postForObject(url, director, Director.class);
//    }
//
//    private boolean directorExists(UUID id) {
//        String url = String.format("%s/%s", directorServiceUrl, id);
//        try {
//            restTemplate.getForObject(url, Director.class);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
}
