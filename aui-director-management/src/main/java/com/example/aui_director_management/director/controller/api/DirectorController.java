package com.example.aui_director_management.director.controller.api;

import com.example.aui_director_management.director.dto.GetDirectorResponse;
import com.example.aui_director_management.director.dto.GetDirectorsResponse;
import com.example.aui_director_management.director.dto.PutDirectorRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


public interface DirectorController {


    @GetMapping("api/directors")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetDirectorsResponse getDirectors();


    @GetMapping("/api/directors/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetDirectorResponse getDirector(
            @PathVariable("id")
            UUID id
    );

    @GetMapping(path = "/api/directors/{id}/photo", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    byte[] getDirectorPhoto(
            @PathVariable("id")
            UUID id
    );

    @PutMapping("/api/directors/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putDirector(
            @PathVariable("id")
            UUID id,
            @RequestBody
            PutDirectorRequest request
    );


    @PutMapping(path = "/api/directors/{id}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void putDirectorPhoto(
            @PathVariable("id")
            UUID id,
            @RequestParam("request")
            MultipartFile request
    );


    @DeleteMapping("/api/directors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDirector(
            @PathVariable("id")
            UUID id
    );



}