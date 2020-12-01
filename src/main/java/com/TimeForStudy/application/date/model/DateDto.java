package com.TimeForStudy.application.date.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Модель определения текущей недели и дня относительно начала текущего семеста.
 *
 * @author Velikanov Artyom.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DateDto {

    /**
     * Номер недели относительно начала семестра.
     */
    private Integer numberWeek;
    /**
     * Номер дня недели.
     */
    private Integer numberDay;
}
