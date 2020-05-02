package com.TimeForStudy.application.classroom.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ClassroomsDto {

    private String label;

    private long id;


    public ClassroomsDto(long id, String name) {
        this.id = id;
        this.label = name;
    }
}
