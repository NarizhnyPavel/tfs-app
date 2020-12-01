package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Модель занятия.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class LessonByDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Номер недели.
     */
    private Integer weekNum = 1;
    /**
     * Тип занятия.
     */
    private Integer type;

    public LessonByDto(Long id, Integer weekNum, Integer type) {
        this.id = id;
        this.weekNum = weekNum;
        this.type = type;
    }
}
