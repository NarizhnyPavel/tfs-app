package com.TimeForStudy.application.user.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProfessorDto {

    private String label;

    private long id;


    public ProfessorDto(long id, String name) {
        this.id = id;
        this.label = name;
    }
}
