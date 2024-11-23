package com.example.aui.controller;

import com.example.aui.Director;
import com.example.aui.DirectorCreateDTO;
import com.example.aui.DirectorDTO;
import com.example.aui.MovieDTO;
import com.example.aui.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/directors")
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

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable UUID id) {
        Director director = directorService.getDirectorById(id);
        return director != null ? ResponseEntity.ok(new DirectorDTO(director)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorCreateDTO dto) {
        Director newDirector = new Director(dto.getName(), dto.getYearOfBirth());
        Director savedDirector = directorService.saveDirector(newDirector);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DirectorDTO(savedDirector));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable UUID id, @RequestBody DirectorCreateDTO dto) {
        Director director = directorService.getDirectorById(id);
        if (director == null) {
            return ResponseEntity.notFound().build();
        }
        director.setName(dto.getName());
        director.setYearOfBirth(dto.getYearOfBirth());
        Director updatedDirector = directorService.saveDirector(director);
        return ResponseEntity.ok(new DirectorDTO(updatedDirector));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable UUID id) {
        Director director = directorService.getDirectorById(id);
        if (director == null) {
            return ResponseEntity.notFound().build();
        }
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }
}
