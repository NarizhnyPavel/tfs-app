package com.TimeForStudy.application.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель поля со свободной информацией.
 *
 * @author Velikanov Artyom.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FieldDto {

    /**
     * Название поля.
     */
    private String name;
    /**
     * Значение поля.
     */
    private String value;

}
