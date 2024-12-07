package com.example.lab3_management.Object.function;

import com.example.lab3_management.Object.dto.GetMovieResponse;
import org.springframework.stereotype.Component;
import com.example.lab3_management.Object.dto.GetDirectorResponse;
import com.example.lab3_management.Object.entity.Director;

import java.util.function.Function;


@Component
public class DirectorToResponseFunction implements Function<Director, GetDirectorResponse> {

    @Override
    public GetDirectorResponse apply(Director entity) {
        return GetDirectorResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .yearOfBirth(entity.getYearOfBirth())
//                .movie(GetDirectorResponse.Movie.builder()
//                        .id(entity.getMovies())
//                        .name(entity.getName())
//                        .date_of_release(entity.getYearOfBirth())
//                        .genre(entity.get)
//                        .build())
                .build();
    }

}
