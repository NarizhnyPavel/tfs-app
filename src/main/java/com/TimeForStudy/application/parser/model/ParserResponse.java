package com.TimeForStudy.application.parser.model;


import lombok.Data;

/**
 * Модель парсинга данных.
 *
 * @author Velikanov Artyom.
 */
@Data
public class ParserResponse {

    /**
     * Статус.
     */
    private String status;
    /**
     * Наименование преподавателя.
     */
    private Integer profnum;
    /**
     * Наименование дисциплины.
     */
    private Integer subjectnum;
    /**
     * Наименование помещения.
     */
    private Integer roomnum;
    /**
     * Наименование группы.
     */
    private Integer groupnum;

}
