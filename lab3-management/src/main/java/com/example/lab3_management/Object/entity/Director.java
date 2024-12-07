package com.example.lab3_management.Object.entity;

import com.example.lab3_management.Object.function.DirectorToResponseFunction;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private final UUID id = UUID.randomUUID();

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