package com.example.aui_director_management.director.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "directors")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode
public class Director implements Serializable {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @ToString.Exclude
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private byte[] photo;


//    @OneToMany(mappedBy = "director", cascade = CascadeType.REMOVE)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private List<Movie> movies;
}