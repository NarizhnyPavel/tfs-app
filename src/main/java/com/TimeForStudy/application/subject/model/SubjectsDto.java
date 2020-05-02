package com.TimeForStudy.application.subject.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SubjectsDto {

    private String label;

    private long id;

    public SubjectsDto(long id, String name) {
        this.id = id;
        this.label = name;
    }
}
