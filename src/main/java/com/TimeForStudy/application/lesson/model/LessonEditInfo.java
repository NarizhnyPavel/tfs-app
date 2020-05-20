package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class LessonEditInfo {

    private long classroomId;

    private String classroom;

    private long professorId;

    private long subjectId;

    private List<AddLessonGroup> groups;

    private String lessonType;

    private String lessonPosition;
}
