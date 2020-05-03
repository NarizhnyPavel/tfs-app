package com.TimeForStudy.application.subject.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * Модель для добавления сущности преподаваемая дисциплина
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddSubjectDto {

    /**
     * Наименование преподаваемой дисциплины
     */
    private String name;

    /**
     * Сокращение
     */
    private String arc;
}
