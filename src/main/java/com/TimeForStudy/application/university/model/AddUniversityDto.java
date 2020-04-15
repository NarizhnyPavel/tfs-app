package com.TimeForStudy.application.university.model;

import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.model.LessonGridDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Модель для добавления сущности учебное заведение
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddUniversityDto {

    /**
     * Наименование университета
     */
    private String name;

    /**
     * Количество деления недель
     */
    private int weeks;

    /**
     * Количество учебных дней
     */
    private String workDays;

    /**
     * Цвет
     */
    private String color;

    /**
     * Логотип
     */
    private String logo;

    /**
     * Длительность одного занятия
     */
    private int lessonDuration;

    public AddUniversityDto(AddUniversityAndLessonGridDto addUniversityAndLessonGridDto) {
        this.name = addUniversityAndLessonGridDto.getName();
        this.weeks = addUniversityAndLessonGridDto.getWeeks();
        this.workDays = addUniversityAndLessonGridDto.getWorkDays();
        this.color = addUniversityAndLessonGridDto.getColor();
        this.logo = addUniversityAndLessonGridDto.getLogotype();
        this.lessonDuration = addUniversityAndLessonGridDto.getLessonDuration();
    }
}
