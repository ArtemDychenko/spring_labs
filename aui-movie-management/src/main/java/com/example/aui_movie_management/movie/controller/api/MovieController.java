package com.example.aui_movie_management.movie.controller.api;


import com.example.aui_movie_management.movie.dto.GetMovieResponse;
import com.example.aui_movie_management.movie.dto.GetMoviesResponse;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.aui_movie_management.movie.dto.PutMovieRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface MovieController {

    @GetMapping("api/movies")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetMoviesResponse getMovies();


    @GetMapping("api/directors/{id}/movies")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetMoviesResponse getDirectorMovies(
            @PathVariable("id")
            UUID id
    );

    @PutMapping("/api/movies/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putMovie(
            @PathVariable("id")
            UUID id,
            @RequestBody
            PutMovieRequest request
    );

    @GetMapping("/api/movies/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetMovieResponse getMovie(
            @PathVariable("id")
            UUID id
    );

    @DeleteMapping("/api/movies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteMovie(
            @PathVariable("id")
            UUID id
    );

}
