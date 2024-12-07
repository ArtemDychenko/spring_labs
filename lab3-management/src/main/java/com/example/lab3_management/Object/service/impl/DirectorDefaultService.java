package com.example.lab3_management.Object.service.impl;

import com.example.lab3_management.Object.entity.Director;

import com.example.lab3_management.Object.repository.DirectorRepository;
import com.example.lab3_management.Object.service.api.DirectorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DirectorDefaultService implements DirectorService {
    private final DirectorRepository directorRepository;
    private final String directorServiceUrl = "http://localhost:8082/api/directors";
    
    @Autowired
    public DirectorDefaultService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }


//    @Transactional
//    public void printAllDirectors() {
//        List<Director> director = directorRepository.findAll();
//        director.forEach(System.out::println);
//    }

    @Override
    public Optional<Director> getDirectorById(UUID id) {
        return directorRepository.findById(id);
    }


    @Override
    public void createDirector(Director director) {
         directorRepository.save(director);
    }


    @Transactional
    public void deleteDirectorByName(String name) {
        Director directorToDelete = directorRepository.findAll().stream()
                .filter(director -> director.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Director with name " + name + " does not exist."));

        directorRepository.delete(directorToDelete);
    }

    @Override
    public void deleteDirector(UUID id) {
        directorRepository.deleteById(id);
    }

    public List<Director> findByName(String name) {
        List<Director> directors = directorRepository.findByName(name);
        if (directors.isEmpty()) {
            throw new IllegalArgumentException("No directors found with name '" + name + "'");
        }
        return directors;
    }


}
