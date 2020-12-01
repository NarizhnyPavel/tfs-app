package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Модель получения информации о номере недели занятия.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class GetInfoLessonDto {

    /**
     * Номер недели.
     */
    private Integer weekNum;
}
