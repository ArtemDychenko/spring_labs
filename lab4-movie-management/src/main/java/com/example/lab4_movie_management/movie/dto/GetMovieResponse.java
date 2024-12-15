package com.example.lab4_movie_management.movie.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetMovieResponse implements Comparable<GetMovieResponse> {

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

        private int yearOfBirth;
    }

    private UUID id;

    private String name;

    private int date_of_release;

    private  int time;

    private String genre;

    private Director director;

    @Override
    public int compareTo(GetMovieResponse other) {
        return this.id.compareTo(other.id);
    }


}