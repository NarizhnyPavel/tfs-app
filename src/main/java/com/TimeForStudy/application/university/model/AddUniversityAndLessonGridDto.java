package com.TimeForStudy.application.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для добавления сущности учебное заведение
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddUniversityAndLessonGridDto {

    /**
     * Наименование университета
     */
    private String name;

    /**
     * Количество деления недель
     */
    private int weeks;

    /**
     * Количество учебных дней
     */
    private Week workDays;

    /**
     * Цвет №1
     */
    private String color1;

    /**
     * Цвет №2
     */
    private String color2;

    /**
     * Цвет №3
     */
    private String color3;

    /**
     * Логотип
     */
    private String logo;

    /**
     * Длительность одного занятия
     */
    private int lessonDuration;

    /**
     * Список сетки занятий
     */
    private LessonGridPosition lessonGridPosition;
}
