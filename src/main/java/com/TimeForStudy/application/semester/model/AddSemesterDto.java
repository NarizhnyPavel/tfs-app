package com.TimeForStudy.application.semester.model;

import com.TimeForStudy.application.university.model.UniversityDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Модель для добавления сущности семестр
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddSemesterDto {

    /**
     * Дата начала
     */
    private LocalDate start;

    /**
     * Дата окончания
     */
    private LocalDate end;

    /**
     * Университет, к которому относится семестр
     */
    private UniversityDto university;
}
