package com.example.aui_movie_management.movie.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutMovieRequest {

    private UUID id;

    private String name;

    private int dateOfRelease;

    private  int time;

    private String genre;

    private UUID director;
}
