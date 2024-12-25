package com.example.aui_movie_management.movie.entity;

import com.example.aui_movie_management.director.entity.Director;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

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
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "dateOfRelease")
    private int dateOfRelease;

    @Column(name = "time")
    private int time;

    @Column(name = "genre")
    private String genre;

    @ManyToOne
    @JoinColumn(name = "director") // Foreign Key
    public Director director;

    @ToString.Exclude
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private byte[] poster;


    @Override
    public int compareTo(Movie other) {
        return this.name.compareTo((other.name));
    }

}