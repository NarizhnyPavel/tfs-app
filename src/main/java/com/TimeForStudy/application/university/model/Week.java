package com.TimeForStudy.application.university.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Модель сетки рабочих дней недели университета.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class Week {

    /**
     * Понедельник.
     */
    private boolean monday = false;
    /**
     * Вторник.
     */
    private boolean tuesday = false;
    /**
     * Среда.
     */
    private boolean wednesday = false;
    /**
     * Четверг.
     */
    private boolean thursday = false;
    /**
     * Пятница.
     */
    private boolean friday = false;
    /**
     * Суббота.
     */
    private boolean saturday = false;
    /**
     * Воскресенье.
     */
    private boolean sunday = false;
}
