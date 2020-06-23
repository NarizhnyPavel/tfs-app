package com.TimeForStudy.application.lessonposition.model;

import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import lombok.Data;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;

import java.time.LocalDate;

/**
 * Модель представления сущности позиция лекции
 *
 * @author Velikanov Artyom
 */
@Data
public class LessonPositionDto {

    private long id;

    /**
     * Неделя.
     */
    private int position;

    /**
     * Номер пары.
     */
    private int number;

    /**
     * День недели.
     */
    private int days;


    public static LessonPositionDto of(LessonPositionEntity lessonPositionEntity) {

        LessonPositionDto dto = new LessonPositionDto();
        dto.setId(lessonPositionEntity.getId());
        dto.setPosition(lessonPositionEntity.getWeek());
        dto.setDays(lessonPositionEntity.getDays());
        dto.setNumber(lessonPositionEntity.getNumber());
        return dto;
    }

    public static LessonPositionEntity on(LessonPositionDto lessonPositionDto) {

        LessonPositionEntity entity  = new LessonPositionEntity();
        entity.setId(lessonPositionDto.getId());
        entity.setWeek(lessonPositionDto.getPosition());
        entity.setDays(lessonPositionDto.getDays());
        entity.setNumber(lessonPositionDto.getNumber());
        return entity;
    }
}
