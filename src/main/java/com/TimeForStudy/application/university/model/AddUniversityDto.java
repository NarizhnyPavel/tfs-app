package com.TimeForStudy.application.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * Модель для добавления сущности учебное заведение
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddUniversityDto {

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
    private String workDays;

    /**
     * Цвет
     */
    private String color;

    /**
     * Логотип
     */
    private String logotype;

    /**
     * Длительность одного занятия
     */
    private int lessonDuration;

}
