package com.TimeForStudy.application.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель роли.
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class RoleDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Наименование роли.
     */
    private String name;
    /**
     * Ранг роли.
     */
    private Integer rank;

}
