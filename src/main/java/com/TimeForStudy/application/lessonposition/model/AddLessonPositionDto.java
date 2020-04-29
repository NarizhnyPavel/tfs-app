package com.TimeForStudy.application.lessonposition.model;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lesson.model.LessonDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для добавления сущности позиция лекции
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddLessonPositionDto {

    /**
     * Лекция
     */
    private LessonDto lesson;

    /**
     * Позиция
     */
    private int position;

    private int number;

    private int days;
}
