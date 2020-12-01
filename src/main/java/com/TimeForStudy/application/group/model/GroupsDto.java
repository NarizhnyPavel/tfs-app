package com.TimeForStudy.application.group.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель списков групп.
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GroupsDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Поле со значением.
     */
    private String label;
    /**
     * Номер группы.
     */
    private Integer number;

}
