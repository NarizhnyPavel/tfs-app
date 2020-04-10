package com.TimeForStudy.application.classroom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * Модель для добавления сущности кабинет
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddClassroomDto {

    /**
     * Номер кабинета
     */
    private int number;
}
