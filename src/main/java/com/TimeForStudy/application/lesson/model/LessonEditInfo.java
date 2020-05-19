package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LessonEditInfo {

    private long classroomId;

    private String classroom;

    private String lessonPosition;
}
