package com.example.aui;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Comparable<Movie>, Serializable {
    @Id
    @Column(name = "id")
    private final UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_release")
    private int date_of_release;

    @Column(name = "time")
    private int time;

    @Column(name = "genre")
    private String genre;

    @ManyToOne
    @JoinColumn(name = "director_id") // Foreign Key
    public Director director;

    private Movie(Builder builder) {
        this.name = builder.name;
        this.date_of_release = builder.date_of_release;
        this.time = builder.time;
        this.genre = builder.genre;
        this.director = builder.director;
    }

    public void setDirector(Director director) {
        this.director = director;
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
        if (!(obj instanceof Movie other)) return false;
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
            this.time = time;
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