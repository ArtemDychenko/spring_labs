package com.example.lab3_management.Object.entity;

import com.example.lab3_management.Object.entity.Director;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode
@Entity
@Table(name="movies")
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
    @JoinColumn(name = "director") // Foreign Key
    public Director director;

//
//
//    public void setDirector(Director director) {
//        this.director = director;
//    }
//
//
    @Override
    public int compareTo(Movie other) {
        return this.name.compareTo((other.name));
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, date_of_release);
//    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (!(obj instanceof Movie other)) return false;
//        return Objects.equals(name, other.name) && Objects.equals(date_of_release, other.date_of_release);
//    }
//
//    @Override
//    public String toString() {
//        String DIRECTOR = director == null ? "No director" : director.toString();
//        return String.format("Movie{name='%s', date of release='%d', time='%d', genre='%s', director='%s'}", name, date_of_release, time, genre, DIRECTOR);
//    }


}