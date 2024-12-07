package com.example.lab3_management.Object.controller.api;


import com.example.lab3_management.Object.dto.GetDirectorResponse;
import com.example.lab3_management.Object.dto.GetDirectorsResponse;
import com.example.lab3_management.Object.dto.GetMovieResponse;
import com.example.lab3_management.Object.dto.GetMoviesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface MovieController {

    @GetMapping("api/movies")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetMoviesResponse getMovies();


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
