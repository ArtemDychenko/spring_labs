package com.example.aui_director_management.movie.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public interface MovieController {
    @DeleteMapping("/api/movies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteMovie(
            @PathVariable("id")
            UUID id
    );
}
