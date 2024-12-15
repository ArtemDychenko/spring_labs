package com.example.lab4_movie_management.director.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.example.lab4_movie_management.movie.entity.Movie;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "directors")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Director implements Serializable {
    @Id
    private UUID id;

//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "year_of_birth")
//    private int yearOfBirth;

//    public Optional<Object> map(DirectorToResponseFunction directorToResponse) {
//    }

//    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Movie> movies = new ArrayList<>();

    @OneToMany(mappedBy = "director", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Movie> movies;
}