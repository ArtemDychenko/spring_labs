package com.example.aui_movie_management.director.service.impl;

import com.example.aui_movie_management.director.entity.Director;
import com.example.aui_movie_management.director.repository.api.DirectorRepository;
import com.example.aui_movie_management.director.service.api.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Service
public class DirectorDefaultService implements DirectorService {
    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorDefaultService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public Optional<Director> getDirectorById(UUID id) {
        return directorRepository.findById(id);
    }


    @Override
    public void createDirector(Director director) {
         directorRepository.save(director);
    }

    @Override
    public void deleteDirector(UUID id) {
        directorRepository.findById(id).ifPresent(directorRepository::delete);
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
