package com.TimeForStudy.application.lessontype.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для добавления сущности тип занятия
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddLessonTypeDto {

    /**
     * Наименование типа занятия
     */
    private String name;
}
