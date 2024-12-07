package com.example.lab3_management.Object.dto;

import com.example.lab3_management.Object.entity.Director;
import lombok.*;

import java.util.List;
import java.util.Objects;
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


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        GetMovieResponse that = (GetMovieResponse) o;
//        return that.date_of_release == date_of_release && Objects.equals(name, that.name) && that.time == time && Objects.equals(genre, that.genre) && Objects.equals(director, that.director);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, date_of_release);
//    }
}