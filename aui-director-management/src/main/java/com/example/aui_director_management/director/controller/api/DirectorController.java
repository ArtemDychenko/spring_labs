package com.example.aui_director_management.director.controller.api;

import com.example.aui_director_management.director.dto.GetDirectorResponse;
import com.example.aui_director_management.director.dto.GetDirectorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/api/directors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDirector(
            @PathVariable("id")
            UUID id
    );



}