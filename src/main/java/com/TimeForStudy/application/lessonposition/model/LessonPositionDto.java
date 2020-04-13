package com.TimeForStudy.application.lessonposition.model;

import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import lombok.Data;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;

/**
 * Модель представления сущности позиция лекции
 *
 * @author Velikanov Artyom
 */
@Data
public class LessonPositionDto {

    private long id;

    /**
     * Позиция
     */
    private int position;

    public static LessonPositionDto of(LessonPositionEntity lessonPositionEntity) {

        LessonPositionDto dto = new LessonPositionDto();
        dto.setId(lessonPositionEntity.getId());
        dto.setPosition(lessonPositionEntity.getPosition());
        return dto;
    }

    public static LessonPositionEntity on(LessonPositionDto lessonPositionDto) {

        LessonPositionEntity entity  = new LessonPositionEntity();
        entity.setId(lessonPositionDto.getId());
        entity.setPosition(lessonPositionDto.getPosition());
        return entity;
    }
}
