package com.example.aui;

import lombok.Data;


@Data
public class MovieCreateDTO {
    private String name;
    private int date_of_release;
    private int time;
    private String genre;
    private String directorId;
}
