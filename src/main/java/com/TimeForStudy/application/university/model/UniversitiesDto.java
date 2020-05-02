package com.TimeForStudy.application.university.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UniversitiesDto {

    private String label;

    private long id;


    public UniversitiesDto(long id, String name) {
        this.id = id;
        this.label = name;
    }
}