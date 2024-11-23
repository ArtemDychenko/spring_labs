package org.example;

import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

@Data
class Movie implements Comparable<Movie>, Serializable {
    final String name;
    final int date_of_release;
    final int time;
    final String genre;
    public Director director;

    private Movie(Builder builder) {
        this.name = builder.name;
        this.date_of_release = builder.date_of_release;
        this.time = builder.time;
        this.genre = builder.genre;
    }



    @Override
    public int compareTo(Movie other) {
        return this.name.compareTo((other.name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date_of_release);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Movie)) return false;
        Movie other = (Movie) obj;
        return Objects.equals(name, other.name) && Objects.equals(date_of_release, other.date_of_release);
    }

    @Override
    public String toString() {
        String DIRECTOR = director == null ? "No director" : director.toString();
        return String.format("Movie{name='%s', date of release='%d', time='%d', genre='%s', director='%s'}", name, date_of_release, time, genre, DIRECTOR);
    }


    public static class Builder {
        private final String name;
        private final int date_of_release;
        private int time;
        private String genre;
        private Director director;

        public Builder(String name, int date_of_release) {
            this.name = name;
            this.date_of_release = date_of_release;
        }

        public Builder time(int time) {
            this.time=time;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder setDirector(Director director) {
            this.director = director;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}