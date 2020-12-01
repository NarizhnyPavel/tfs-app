package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Модель редактирования занятия.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class LessonEditInfo {

    /**
     * Идентификатор помещения.
     */
    private Long classroomId;
    /**
     * Идентификатор номер помещения.
     */
    private String classroom;
    /**
     * Идентификатор преподавателя.
     */
    private Long professorId;
    /**
     * Идентификатор дисциплины.
     */
    private Long subjectId;
    /**
     * Список учебных групп.
     */
    private List<AddLessonGroup> groups;
    /**
     * Тип занятия.
     */
    private String lessonType;
    /**
     * Позиция занятия.
     */
    private String lessonPosition;
}
