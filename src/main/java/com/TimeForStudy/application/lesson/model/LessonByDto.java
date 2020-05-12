package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LessonByDto {

    private long id;

    private int weekNum;

    private int type;

    public LessonByDto(long id, int weekNum, int type) {
        this.id = id;
        this.weekNum = weekNum;
        this.type = type;
    }
}
