package com.TimeForStudy.application.subject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель представления сущности преподаваемой дисциплины.
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class SubjectDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Наименование преподаваемой дисциплины
     */
    private String name;
    /**
     * Сокращение
     */
    private String arc;
}
