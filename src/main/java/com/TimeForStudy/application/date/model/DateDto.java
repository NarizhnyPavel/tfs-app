package com.TimeForStudy.application.date.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Модель определения текущей недели и дня относительно начала текущего семеста.
 *
 * @author Velikanov Artyom.
 */
@Data
@RequiredArgsConstructor
public class DateDto {

    // номер недели относительно начала семестра
    private int numberWeek;
    // номер дня недели
    private  int numberDay;

    public DateDto(int numberWeek, int numberDay) {
        this.numberDay = numberDay;
        this.numberWeek = numberWeek;
    }
}
