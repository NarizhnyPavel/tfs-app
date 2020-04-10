package com.TimeForStudy.application.group.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Модель для добавления сущности группа
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddGroupDto {

    /**
     * Номер группы
     */
    private String number;
}
