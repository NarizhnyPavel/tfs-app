package com.TimeForStudy.application.lesson.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель валидации занятия.
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class SearchDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Поле с значением.
     */
    private String label;
    /**
     * Тип валидации.
     */
    private Integer type;

}
