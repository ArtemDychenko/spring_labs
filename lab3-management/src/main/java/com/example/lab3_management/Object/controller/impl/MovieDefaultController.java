package com.example.lab3_management.Object.controller.impl;

import com.example.lab3_management.Object.controller.api.MovieController;
import com.example.lab3_management.Object.dto.GetDirectorResponse;
import com.example.lab3_management.Object.dto.GetMovieResponse;
import com.example.lab3_management.Object.dto.GetMoviesResponse;
import com.example.lab3_management.Object.function.MovieToResponseFunction;
import com.example.lab3_management.Object.function.MoviesToResponseFunction;
import com.example.lab3_management.Object.service.impl.MovieDefaultService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;


@RestController
@Log
public class MovieDefaultController implements MovieController {

    private final MovieDefaultService movieService;

    private final MoviesToResponseFunction moviesToResponse;

    private final MovieToResponseFunction movieToResponse;

    private final String directorServiceUrl = "http://localhost:8082/api/directors";


    @Autowired
    public MovieDefaultController(MovieDefaultService movieService, MoviesToResponseFunction moviesToResponse, MovieToResponseFunction movieToResponse) {
        this.movieService = movieService;
        this.moviesToResponse = moviesToResponse;
        this.movieToResponse = movieToResponse;
    }

    @Override
    public GetMoviesResponse getMovies() {
        return moviesToResponse.apply(movieService.getAllMovies());
    }

    @Override
    public GetMovieResponse getMovie (UUID id) {
        return movieService.getMovieById(id)
                .map(movieToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
//
//    @GetMapping("/genre/{genre}")
//    public List<GetMovieResponse> getMoviesByGenre(@PathVariable String genre) {
//        return movieService.getMoviesByGenre(genre).stream()
//                .map(GetMovieResponse::new)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    @GetMapping("/director/{id}")
//    public List<GetMovieResponse> getMoviesByDirector(@PathVariable UUID id) {
//        return movieService.getAllMovies().stream()
//                .filter(Movie -> Movie.getDirector().getId().equals(id))
//                .map(GetMovieResponse::new)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    @PostMapping
//    public ResponseEntity<GetMovieResponse> createMovie(@RequestBody GetMoviesResponse dto) {
//        Director director = getDirectorById(UUID.fromString(dto.getDirectorId()));
//        if (director == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//        System.out.println(director);
//
//        Movie movie = new Movie.Builder(dto.getName(), dto.getDate_of_release())
//                .time(dto.getTime())
//                .genre(dto.getGenre())
//                .setDirector(director)
//                .build();
//        Movie savedMovie = movieService.saveMovie(movie);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new GetMovieResponse(savedMovie));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<GetMovieResponse> updateMovie(@PathVariable UUID id, @RequestBody GetMoviesResponse dto) {
//        Movie movie = movieService.getMovieById(id);
//        if (movie == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Director director = getDirectorById(UUID.fromString(dto.getDirectorId()));
//        if (director == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        movie.setName(dto.getName());
//        movie.setDate_of_release(dto.getDate_of_release());
//        movie.setTime(dto.getTime());
//        movie.setGenre(dto.getGenre());
//        movie.setDirector(director);
//
//        Movie updatedMovie = movieService.saveMovie(movie);
//        return ResponseEntity.ok(new GetMovieResponse(updatedMovie));
//    }
//
    @Override
    public void deleteMovie(@PathVariable UUID id) {
        movieService.getMovieById(id)
                        .ifPresentOrElse(
                                Movie -> movieService.deleteMovie(id),
                                () -> {
                                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                                }
                        );

    }

    //================
    // Metody pomocnicze
    //============

//    private Director getDirectorById(UUID id) {
//        String url = String.format("%s/%s", directorServiceUrl, id);
//        try {
//            return restTemplate.getForObject(url, Director.class);
//        } catch (Exception e) {
//            System.err.println("Error fetching director with ID: " + id);
//            return null;
//        }
//    }
}
