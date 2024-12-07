package com.example.lab3_management.Object.controller.impl;

import com.example.lab3_management.Object.controller.api.DirectorController;

import com.example.lab3_management.Object.entity.Director;
import com.example.lab3_management.Object.function.DirectorToResponseFunction;
import com.example.lab3_management.Object.function.DirectorsToResponseFunction;
import com.example.lab3_management.Object.service.impl.DirectorDefaultService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.lab3_management.Object.dto.GetDirectorResponse;
import com.example.lab3_management.Object.dto.GetDirectorsResponse;
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



//
//    @GetMapping("/{id}")
//    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable UUID id) {
//        Director director = directorService.getDirectorById(id);
//        return director != null ? ResponseEntity.ok(new DirectorDTO(director)) : ResponseEntity.notFound().build();
//    }
//
//    @PostMapping
//    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorCreateDTO dto) {
//        Director newDirector = new Director(dto.getName(), dto.getYearOfBirth());
//        Director savedDirector = directorService.saveDirector(newDirector);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new DirectorDTO(savedDirector));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable UUID id, @RequestBody DirectorCreateDTO dto) {
//        Director director = directorService.getDirectorById(id);
//        if (director == null) {
//            return ResponseEntity.notFound().build();
//        }
//        director.setName(dto.getName());
//        director.setYearOfBirth(dto.getYearOfBirth());
//        Director updatedDirector = directorService.saveDirector(director);
//        return ResponseEntity.ok(new DirectorDTO(updatedDirector));
//    }
//
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


