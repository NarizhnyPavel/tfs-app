package com.TimeForStudy.application.group.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель студентов группы.
 *
 * @author Velikanov Artyom.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UsersByGroup {

    /**
     * Номер группы.
     */
    private Integer number;
    /**
     * Роль.
     */
    private Integer role;
    /**
     * Наименование.
     */
    private String name;
    /**
     * Номер телефона.
     */
    private String phone;

}
