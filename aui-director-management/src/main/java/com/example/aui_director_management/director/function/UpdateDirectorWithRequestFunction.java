package com.example.aui_director_management.director.function;

import com.example.aui_director_management.director.dto.PatchDirectorRequest;
import com.example.aui_director_management.director.entity.Director;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UpdateDirectorWithRequestFunction implements BiFunction<Director, PatchDirectorRequest, Director> {

    @Override
    public Director apply(Director entity, PatchDirectorRequest request) {
        return Director.builder()
                .name(request.getName())
                .yearOfBirth(request.getYearOfBirth())
//                .director(entity.getDirector())
                .photo(entity.getPhoto())
                .build();
    }
}
