package com.example.lab3_management.Object.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.lab3_management.Object.dto.GetDirectorsResponse;
import com.example.lab3_management.Object.dto.GetDirectorResponse;

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