package com.example.aui_director_management.movie.controller.impl;

import com.example.aui_director_management.movie.service.impl.MovieDefaultService;
import com.example.aui_director_management.movie.controller.api.MovieController;
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

    @Autowired
    public MovieDefaultController(MovieDefaultService movieService) {
        this.movieService = movieService;
    }

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
