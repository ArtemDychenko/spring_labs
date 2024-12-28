package com.example.aui_movie_management.director.controller.impl;

import com.example.aui_movie_management.director.controller.api.DirectorController;
import com.example.aui_movie_management.director.entity.Director;
import com.example.aui_movie_management.director.service.impl.DirectorDefaultService;
import com.example.aui_movie_management.movie.entity.Movie;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

@RestController
@Log
public class DirectorDefaultController implements DirectorController {

    private final DirectorDefaultService directorService;

    @Autowired
    public DirectorDefaultController(DirectorDefaultService directorService) {
        this.directorService = directorService;
    }

    @Override
    public void deleteDirector(UUID id) {
        directorService.getDirectorById(id)
                .ifPresentOrElse(
                        director -> directorService.deleteDirector(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }

}


