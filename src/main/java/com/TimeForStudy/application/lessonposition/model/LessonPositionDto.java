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

    /**
     * Статус.
     */
    private boolean status;

    /**
     * Время.
     */
    private LocalDate time;

    public static LessonPositionDto of(LessonPositionEntity lessonPositionEntity) {

        LessonPositionDto dto = new LessonPositionDto();
        dto.setId(lessonPositionEntity.getId());
        dto.setPosition(lessonPositionEntity.getPosition());
        dto.setDays(lessonPositionEntity.getDays());
        dto.setNumber(lessonPositionEntity.getNumber());
        dto.setStatus(lessonPositionEntity.isStatus());
        dto.setTime(lessonPositionEntity.getTime());
        return dto;
    }

    public static LessonPositionEntity on(LessonPositionDto lessonPositionDto) {

        LessonPositionEntity entity  = new LessonPositionEntity();
        entity.setId(lessonPositionDto.getId());
        entity.setPosition(lessonPositionDto.getPosition());
        entity.setDays(lessonPositionDto.getDays());
        entity.setNumber(lessonPositionDto.getNumber());
        entity.setStatus(lessonPositionDto.isStatus());
        entity.setTime(lessonPositionDto.getTime());
        return entity;
    }
}
