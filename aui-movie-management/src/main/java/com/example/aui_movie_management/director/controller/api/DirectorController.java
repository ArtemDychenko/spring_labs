package com.example.aui_movie_management.director.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

public interface DirectorController {
    @DeleteMapping("/api/directors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDirector(@PathVariable("id") UUID id);

}

