package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Модель типа валидации.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class TypeSearch {

    /**
     * Признак занятости преподавателя.
     */
    private boolean professor;
    /**
     * Признак занятости группы.
     */
    private boolean group;
    /**
     * Признак занятости помещения.
     */
    private boolean classroom;
}
