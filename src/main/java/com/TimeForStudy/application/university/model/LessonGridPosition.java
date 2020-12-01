package com.TimeForStudy.application.university.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель сетки позиций занятий.
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class LessonGridPosition {

    /**
     * Первая позиция.
     */
    private String position1;
    /**
     * Вторая позиция.
     */
    private String position2;
    /**
     * Третья позиция.
     */
    private String position3;
    /**
     * Четвёртая позиция.
     */
    private String position4;
    /**
     * Пятая позиция.
     */
    private String position5;
    /**
     * Шестая позиция.
     */
    private String position6;
    /**
     * Седьмая позиция.
     */
    private String position7;

}
