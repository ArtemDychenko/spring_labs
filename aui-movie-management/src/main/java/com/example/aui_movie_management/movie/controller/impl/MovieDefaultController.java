package com.example.aui_movie_management.movie.controller.impl;

import com.example.aui_movie_management.movie.controller.api.MovieController;
import com.example.aui_movie_management.movie.dto.GetMovieResponse;
import com.example.aui_movie_management.movie.dto.GetMoviesResponse;
import com.example.aui_movie_management.movie.dto.PutMovieRequest;
import com.example.aui_movie_management.movie.function.MovieToResponseFunction;
import com.example.aui_movie_management.movie.function.MoviesToResponseFunction;
import com.example.aui_movie_management.movie.function.RequestToMovieFunction;
import com.example.aui_movie_management.movie.service.impl.MovieDefaultService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;


@RestController
@Log
public class MovieDefaultController implements MovieController {

    private final MovieDefaultService movieService;

    private final MoviesToResponseFunction moviesToResponse;

    private final MovieToResponseFunction movieToResponse;

    private final RequestToMovieFunction requestToMovie;

    @Autowired
    public MovieDefaultController(MovieDefaultService movieService, MoviesToResponseFunction moviesToResponse, MovieToResponseFunction movieToResponse, RequestToMovieFunction requestToMovie) {
        this.movieService = movieService;
        this.moviesToResponse = moviesToResponse;
        this.movieToResponse = movieToResponse;
        this.requestToMovie = requestToMovie;
    }

    @Override
    public GetMoviesResponse getMovies() {
        return moviesToResponse.apply(movieService.getAllMovies());
    }

    @Override
    public GetMoviesResponse getDirectorMovies(UUID id) {
        return movieService.findAllByDirector(id)
                .map(moviesToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void putMovie(UUID id, PutMovieRequest request) {
        movieService.createMovie(requestToMovie.apply(id, request));
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

}
