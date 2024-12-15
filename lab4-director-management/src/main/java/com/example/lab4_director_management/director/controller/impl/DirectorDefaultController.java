package com.example.lab4_director_management.director.controller.impl;

import com.example.lab4_director_management.director.controller.api.DirectorController;
import com.example.lab4_director_management.director.dto.GetDirectorResponse;
import com.example.lab4_director_management.director.dto.GetDirectorsResponse;
import com.example.lab4_director_management.director.function.DirectorToResponseFunction;
import com.example.lab4_director_management.director.function.DirectorsToResponseFunction;
import com.example.lab4_director_management.director.service.impl.DirectorDefaultService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log
public class DirectorDefaultController implements DirectorController {
    private final DirectorDefaultService directorService;

    private final DirectorToResponseFunction directorToResponse;

    private final DirectorsToResponseFunction directorsToResponse;

    @Autowired
    public DirectorDefaultController(DirectorDefaultService directorService, DirectorToResponseFunction directorToResponse, DirectorsToResponseFunction directorsToResponse) {
        this.directorService = directorService;
        this.directorToResponse = directorToResponse;
        this.directorsToResponse = directorsToResponse;
    }



    @Override
    public GetDirectorsResponse getDirectors() {
        return directorsToResponse.apply(directorService.getAllDirectors());
    }


    @Override
    public GetDirectorResponse getDirector (UUID id) {
        return directorService.getDirectorById(id)
                .map(directorToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



    @Override
    public void deleteDirector(@PathVariable UUID id) {
        directorService.getDirectorById(id)
                .ifPresentOrElse(
                        director -> directorService.deleteDirector(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );

    }
}


