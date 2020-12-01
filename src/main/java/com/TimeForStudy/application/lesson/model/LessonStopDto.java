package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Модель остановки занятия.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class LessonStopDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Номер недели.
     */
    private Integer weeks;

}
