package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddLessonGroup {

    private long id;

    private String label;

    private int number = 1;
}
