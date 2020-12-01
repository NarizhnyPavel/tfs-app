package com.TimeForStudy.application.lessonposition.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель представления сущности позиция лекции.
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class LessonPositionDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Неделя.
     */
    private Integer week;
    /**
     * Номер пары.
     */
    private Integer lessonNumber;
    /**
     * День недели.
     */
    private Integer dayNumber;

}
