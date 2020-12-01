package com.TimeForStudy.application.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель идентифицируемой сущности.
 *
 * @author Velikanov Artyom.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class IdNameDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Название.
     */
    private String name;

}
