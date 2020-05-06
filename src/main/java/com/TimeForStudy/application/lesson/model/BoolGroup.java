package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BoolGroup {

    private long id;

    private int number;

    public BoolGroup(long id, int number) {
        this.id = id;
        this.number = number;
    }
}
