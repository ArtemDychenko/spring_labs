package com.example.aui_director_management.director.dto;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetDirectorResponse implements Comparable<GetDirectorResponse> {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Movie {
        private UUID id;

        private String name;

        private int date_of_release;

        private int time;

        private String genre;
    }

    private UUID id;
    private String name;
    private int yearOfBirth;

//    @Singular
//    private List<Movie> movies;

    @Override
    public int compareTo(GetDirectorResponse other) {
        return this.id.compareTo(other.id);
    }

}
