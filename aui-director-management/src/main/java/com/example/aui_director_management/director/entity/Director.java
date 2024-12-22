package com.example.aui_director_management.director.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
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
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

//    public Optional<Object> map(DirectorToResponseFunction directorToResponse) {
//    }

//    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Movie> movies = new ArrayList<>();

//    @OneToMany(mappedBy = "director", cascade = CascadeType.REMOVE)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private List<Movie> movies;
}