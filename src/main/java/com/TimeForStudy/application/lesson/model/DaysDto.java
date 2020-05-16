package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class DaysDto {

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
