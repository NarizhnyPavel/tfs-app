package com.TimeForStudy.application.lesson.model;

import lombok.Data;

/**
 * Модель редактирования позиции занятия.
 *
 * @author Velikanov Artyom
 */
@Data
public class UpdatePosition {

    /**
     * Идентификатор предыдущего преподавателя.
     */
    private Long oldPositionId;
    /**
     * Номер новой позиции.
     */
    private String newPositionNum;
    /**
     * Идентификатор нового помещения.
     */
    private Long newClassroomId;
}
