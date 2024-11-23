package com.example.aui;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "directors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Director implements Serializable {
    @Id
    @Column(name = "id")
    private final UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movies = new ArrayList<>();
    ;

    public Director(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.movies = new ArrayList<>();

    }


    public void addMovie(Movie movie) {
        movie.setDirector(this);
        movies.add(movie);
    }

    @Override
    public String toString() {
        String moviesList = movies == null ? "No movies" :
                movies.stream()
                        .map(Movie::getName)
                        .collect(Collectors.joining(", "));
        return String.format("Director{name='%s', born=%d, movies=[%s]}", name, yearOfBirth, moviesList);
    }
}