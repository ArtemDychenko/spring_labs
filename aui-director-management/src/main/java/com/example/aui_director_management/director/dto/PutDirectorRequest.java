package com.example.aui_director_management.director.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutDirectorRequest {

    private UUID id;

    private String name;

    private int yearOfBirth;

}
