package com.TimeForStudy.application.lessongrid.model;

import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Модель представления сущности сетки расписания
 *
 * @author Velikanov Artyom
 */
@Data
public class LessonGridDto {

    private long id;

    /**
     * Номер пары
     */
    private int lessonNumber;

    /**
     * Время лекции
     */
    private String time;

    public static LessonGridDto of(LessonGridEntity lessonGridEntity) {
        LessonGridDto dto = new LessonGridDto();
        dto.setId(lessonGridEntity.getId());
        dto.setLessonNumber(lessonGridEntity.getLessonNumber());
        dto.setTime(lessonGridEntity.getTime());
        return dto;
    }

    public static LessonGridEntity on(LessonGridDto lessonGridDto) {
        LessonGridEntity entity = new LessonGridEntity();
        entity.setId(lessonGridDto.getId());
        entity.setLessonNumber(lessonGridDto.getLessonNumber());
        entity.setTime(lessonGridDto.getTime());
        return entity;
    }
}
