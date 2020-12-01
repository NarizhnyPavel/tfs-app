package com.TimeForStudy.application.lesson.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Модель краткой информации о занятии.
 *
 * @author Velikanov Artyom
 */
@Data
@AllArgsConstructor(staticName = "of")
public class BoolLessonDto {

    /**
     *  Позиция пары
     */
    private String position;
    /**
     *  Кабинет, в котором проходит занятие
     */
    private Integer classroom;
    /**
     * Преподаватель, который проводит занятие
     */
    private Integer professor;
    /**
     * Список Групп.
     */
    private List<AddLessonGroup> groups;

    public BoolLessonDto() {
        this.classroom = 1;
        this.professor = 1;
    }
}
