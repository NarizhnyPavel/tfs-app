package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Модель валидации.
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class ValidateSearch {

    /**
     * Запрос.
     */
    private String request;
    /**
     * Тип валидации.
     */
    private TypeSearch type;
}
