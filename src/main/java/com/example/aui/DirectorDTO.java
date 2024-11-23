package com.example.aui;

import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class DirectorDTO implements Comparable<DirectorDTO> {
    private final String id;
    private final String name;
    private final int yearOfBirth;
    private final List<String> movies;

    public DirectorDTO(Director director) {
        this.id = director.getId().toString();
        this.name = director.getName();
        this.yearOfBirth = director.getYearOfBirth();
        this.movies = director.getMovies().stream()
                .map(Movie::getName)
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(DirectorDTO other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorDTO that = (DirectorDTO) o;
        return yearOfBirth == that.yearOfBirth && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearOfBirth);
    }
}
