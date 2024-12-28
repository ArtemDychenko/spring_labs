package com.example.aui_director_management.director.service.api;

import com.example.aui_director_management.director.entity.Director;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface DirectorService {

    Optional<Director> getDirectorById(UUID id);

    void createDirector(Director director);

    List<Director> getAllDirectors();

    void updateDirector(Director director);

    void deleteDirector(UUID id);

    Optional<byte[]> findPhoto(UUID id);

    void updatePhoto(UUID id, InputStream is);
}
