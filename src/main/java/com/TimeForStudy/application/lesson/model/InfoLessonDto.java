package com.TimeForStudy.application.lesson.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class InfoLessonDto {

    /**
     *  Время.
     */
    private String time;

    /**
     *  Кабинет, в котором проходит занятие
     */
    private int classroom;

    /**
     * Предмет преподаваемый на занятии
     */
    private String subject;

    /**
     * Статус
     */
    private boolean status;

    /**
     * Преподаватель, который проводит занятие
     */
    private String professor;

    /**
     * Тип занятия
     */
    private String lessonType;

    public InfoLessonDto( String time, int classroom, String subject, boolean status, String professor, String lessonType) {
        this.time = time;
        this.classroom = classroom;
        this.subject = subject;
        this.status = status;
        this.professor = professor;
        this.lessonType = lessonType;
    }

}
