package com.example.lab4_director_management.director.function;

import com.example.lab4_director_management.director.dto.GetDirectorsResponse;
import com.example.lab4_director_management.director.entity.Director;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;


@Component
public class DirectorsToResponseFunction implements Function<List<Director>, GetDirectorsResponse> {

    @Override
    public GetDirectorsResponse apply(List<Director> entities) {
        return GetDirectorsResponse.builder()
                .directors(entities.stream()
                        .map(director -> GetDirectorsResponse.Director.builder()
                                .id(director.getId())
                                .name(director.getName())
                                .yearOfBirth(Integer.toString(director.getYearOfBirth()))
                                .build())
                        .toList())
                .build();
    }

}
