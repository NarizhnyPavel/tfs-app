package com.TimeForStudy.application.lessongrid.model;

import com.TimeForStudy.application.university.model.UniversityDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для добавления сущности сетки расписания
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddLessonGridDto {

    /**
     * Номер пары
     */
    private int lessonNumber;

    /**
     * Время лекции
     */
    private String time;

    /**
     * Университет
     */
    private UniversityDto university;
}
