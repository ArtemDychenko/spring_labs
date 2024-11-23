package com.example.aui.controller;

import com.example.aui.Director;
import com.example.aui.DirectorDTO;
import com.example.aui.MovieDTO;
import com.example.aui.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/directors")
public class DirectorController {
    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<DirectorDTO> getAllDirectors() {
        return directorService.getAllDirectors().stream()
                .map(DirectorDTO::new)
                .collect(Collectors.toList());
    }
}
