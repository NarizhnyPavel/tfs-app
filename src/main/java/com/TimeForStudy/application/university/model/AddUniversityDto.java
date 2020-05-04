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
     * Цвет №1
     */
    private String color1;

    /**
     * Цвет №2
     */
    private String color2;

    /**
     * Цвет №3
     */
    private String color3;

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
        this.color1 = addUniversityAndLessonGridDto.getColor1();
        this.color2 = addUniversityAndLessonGridDto.getColor2();
        this.color3 = addUniversityAndLessonGridDto.getColor3();
        this.logo = addUniversityAndLessonGridDto.getLogo();
        this.lessonDuration = addUniversityAndLessonGridDto.getLessonDuration();
    }
}
