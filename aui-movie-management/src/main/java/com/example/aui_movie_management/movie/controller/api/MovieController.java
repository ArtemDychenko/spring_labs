package com.example.aui_movie_management.movie.controller.api;


import com.example.aui_movie_management.movie.dto.GetMovieResponse;
import com.example.aui_movie_management.movie.dto.GetMoviesResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.aui_movie_management.movie.dto.PutMovieRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(path = "/api/movies/{id}/poster", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    byte[] getMoviePoster(
            @PathVariable("id")
            UUID id
    );

    @PutMapping(path = "/api/movies/{id}/poster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void putMoviePoster(
            @PathVariable("id")
            UUID id,
            @RequestParam("request")
            MultipartFile request
    );


    @DeleteMapping("/api/movies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteMovie(
            @PathVariable("id")
            UUID id
    );

}
