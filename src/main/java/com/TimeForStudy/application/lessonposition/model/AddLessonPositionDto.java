package com.TimeForStudy.application.lessonposition.model;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lesson.model.LessonDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Модель для добавления сущности позиция лекции
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddLessonPositionDto {

    /**
     * Лекция.
     */
    private LessonDto lesson;

    /**
     * Неделя.
     */
    private int position;

    /**
     * Номер пары.
     */
    private int number;

    /**
     * День недели.
     */
    private int days;

    /**
     * Статус.
     */
    private boolean status;

    /**
     * Время.
     */
    private LocalDate time;
}
