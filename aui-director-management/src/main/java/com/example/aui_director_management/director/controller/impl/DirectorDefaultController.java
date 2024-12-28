package com.example.aui_director_management.director.controller.impl;

import com.example.aui_director_management.director.controller.api.DirectorController;
import com.example.aui_director_management.director.dto.GetDirectorResponse;
import com.example.aui_director_management.director.dto.GetDirectorsResponse;
import com.example.aui_director_management.director.dto.PutDirectorRequest;
import com.example.aui_director_management.director.entity.Director;
import com.example.aui_director_management.director.function.DirectorToResponseFunction;
import com.example.aui_director_management.director.function.DirectorsToResponseFunction;
import com.example.aui_director_management.director.function.RequestToDirectorFunction;
import com.example.aui_director_management.director.service.impl.DirectorDefaultService;
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

    private final DirectorToResponseFunction directorToResponse;

    private final DirectorsToResponseFunction directorsToResponse;

    private final RequestToDirectorFunction requestToDirector;

    @Autowired
    public DirectorDefaultController(DirectorDefaultService directorService, DirectorToResponseFunction directorToResponse, DirectorsToResponseFunction directorsToResponse, RequestToDirectorFunction requestToDirector) {
        this.directorService = directorService;
        this.directorToResponse = directorToResponse;
        this.directorsToResponse = directorsToResponse;
        this.requestToDirector = requestToDirector;
    }



    @Override
    public GetDirectorsResponse getDirectors() {
        return directorsToResponse.apply(directorService.getAllDirectors());
    }

    @Override
    public void putDirector(UUID id, PutDirectorRequest request) {
        directorService.createDirector(requestToDirector.apply(id, request));
    }

    @Override
    public GetDirectorResponse getDirector (UUID id) {
        return directorService.getDirectorById(id)
                .map(directorToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public byte[] getDirectorPhoto(@PathVariable UUID id) {
        return directorService.getDirectorById(id)
                .map(Director::getPhoto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void putDirectorPhoto(@PathVariable UUID id, MultipartFile request) {
        directorService.getDirectorById(id)
                .ifPresentOrElse(director -> {
                            try {
                                directorService.updatePhoto(id, request.getInputStream());
                            } catch (IOException ex) {
                                log.log(Level.WARNING, ex.getMessage(), ex);
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                            }
                        },
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
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


