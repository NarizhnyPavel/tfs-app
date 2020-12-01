package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Модель учебного дня.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class DayDto {

    /**
     * Наименование дня недели.
     */
    private String dayName;
    /**
     * Дата (ДД месяц).
     */
    private String date;
    /**
     * Текущий день или нет.
     */
    private boolean status = false;
    /**
     * Список расписаний на каждый день желаемой недели.
     */
    private List<InfoLessonDto> infoLessonDtos;
}
