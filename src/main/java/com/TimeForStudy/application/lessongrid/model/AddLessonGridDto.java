package com.TimeForStudy.application.lessongrid.model;

import com.TimeForStudy.application.university.model.UniversityDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для добавления сущности места занятия в сетке
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddLessonGridDto {

    /**
     * Сборный номер лекции (123: 1 - номер недели, 2 - номер дня недели, 3 - номер пары)
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
