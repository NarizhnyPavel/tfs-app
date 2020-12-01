package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Модель позиции занятия.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class PositionDto {

    /**
     * Номер позиции.
     */
    private String num;
    /**
     * Текст позиции.
     */
    private String text;
}
