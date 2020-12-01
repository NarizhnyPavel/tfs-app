package com.TimeForStudy.application.semester.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Модель представления сущности семестр.
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class SemesterDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Дата начала.
     */
    private LocalDate startDate;
    /**
     * Дата окончания.
     */
    private LocalDate endDate;

}
