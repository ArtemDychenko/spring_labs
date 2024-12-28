package com.example.aui_director_management.director.function;

import com.example.aui_director_management.director.dto.PutDirectorRequest;
import com.example.aui_director_management.director.entity.Director;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToDirectorFunction implements BiFunction<UUID, PutDirectorRequest, Director> {

    @Override
    public Director apply(UUID id, PutDirectorRequest request) {
        return Director.builder()
                .id(id)
                .name(request.getName())
                .yearOfBirth(request.getYearOfBirth())
//                .movies(Movie.builder()
//                        .id(entity.getMovies())
//                        .name(entity.getName())
//                        .date_of_release(entity.getYearOfBirth())
//                        .genre(entity.get)
//                        .build())
                .build();
    }
}
