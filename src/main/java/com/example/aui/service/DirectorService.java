package com.example.aui.service;

import com.example.aui.Director;
import com.example.aui.Movie;
import com.example.aui.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Transactional
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Transactional
    public void printAllDirectors() {
        List<Director> director = directorRepository.findAll();
        director.forEach(System.out::println);
    }

    public Director getDirectorById(UUID id) {
        return directorRepository.findById(id).orElse(null);
    }

    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    @Transactional
    public void deleteDirectorByName(String name) {
        Director directorToDelete = directorRepository.findAll().stream()
                .filter(director -> director.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Director with name " + name + " does not exist."));

        // Usunięcie reżysera spowoduje automatyczne usunięcie powiązanych filmów, dzięki kaskadowemu usuwaniu
        directorRepository.delete(directorToDelete);
    }


    public void deleteDirector(UUID id) {
        directorRepository.deleteById(id);
    }
}
