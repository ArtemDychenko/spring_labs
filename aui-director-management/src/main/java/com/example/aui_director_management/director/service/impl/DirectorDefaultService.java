package com.example.aui_director_management.director.service.impl;

import com.example.aui_director_management.director.entity.Director;
import com.example.aui_director_management.director.event.repository.api.DirectorEventRepository;
import com.example.aui_director_management.director.repository.DirectorRepository;
import com.example.aui_director_management.director.service.api.DirectorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DirectorDefaultService implements DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorEventRepository eventRepository;

    @Autowired
    public DirectorDefaultService(DirectorRepository directorRepository, DirectorEventRepository eventRepository) {
        this.directorRepository = directorRepository;
        this.eventRepository = eventRepository;
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

    @Override
    public void updateDirector(Director director) {
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
        eventRepository.deleteDirector(id);
    }

    public List<Director> findByName(String name) {
        List<Director> directors = directorRepository.findByName(name);
        if (directors.isEmpty()) {
            throw new IllegalArgumentException("No directors found with name '" + name + "'");
        }
        return directors;
    }

    @Override
    public Optional<byte[]> findPhoto(UUID id) {
        return directorRepository.findPhotoById(id);
    }

    @Override
    public void updatePhoto(UUID id, InputStream is) {
        directorRepository.findById(id).ifPresent(director -> {
            try {
                director.setPhoto(is.readAllBytes());
                directorRepository.save(director);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }
}
