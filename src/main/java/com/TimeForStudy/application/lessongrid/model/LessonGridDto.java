package com.TimeForStudy.application.lessongrid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель представления сущности сетки расписания.
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class LessonGridDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Номер пары.
     */
    private Integer lessonNumber;
    /**
     * Время лекции.
     */
    private String time;

}
