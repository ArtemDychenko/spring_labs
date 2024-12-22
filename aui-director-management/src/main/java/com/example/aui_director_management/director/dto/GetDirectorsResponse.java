package com.example.aui_director_management.director.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class GetDirectorsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Director {
        private UUID id;
        private String name;
        private String yearOfBirth;
    }

    private List<Director> directors;

}