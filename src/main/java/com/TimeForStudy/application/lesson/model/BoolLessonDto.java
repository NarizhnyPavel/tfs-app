package com.TimeForStudy.application.lesson.model;


import lombok.Data;
import java.util.List;

@Data
public class BoolLessonDto {

    /**
     *  Кабинет, в котором проходит занятие
     */
    private int classroom;

    /**
     * Преподаватель, который проводит занятие
     */
    private int professor;

    /**
     * Список Групп.
     */
    private List<AddLessonGroup> groups;

    public BoolLessonDto() {
        this.classroom = 1;
        this.professor = 1;
    }
}
