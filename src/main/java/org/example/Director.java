package org.example;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
class Director implements Serializable {
    private final String name;
    private final int date_of_born;
    private List<Movie> movies;

    public Director(String name, int date_of_born) {
        this.name = name;
        this.date_of_born = date_of_born;
        this.movies = new ArrayList<>();

    }


    public int getAge() {
        return date_of_born;
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
        return String.format("Director{name='%s', born=%d, movies=[%s]}", name, date_of_born, moviesList);
    }
}