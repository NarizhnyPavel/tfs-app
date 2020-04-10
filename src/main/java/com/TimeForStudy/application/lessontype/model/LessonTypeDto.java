package com.TimeForStudy.application.lessontype.model;

import com.TimeForStudy.application.lessontype.domain.LessonTypeEntity;
import lombok.Data;


/**
 * Модель представления сущности тип занятия
 *
 * @author Velikanov Artyom
 */
@Data
public class LessonTypeDto {

    private long id;

    /**
     * Наименование типа занятия
     */
    private String name;

    public static LessonTypeDto of(LessonTypeEntity lessonTypeEntity) {
        LessonTypeDto dto = new LessonTypeDto();
        dto.setId(lessonTypeEntity.getId());
        dto.setName(lessonTypeEntity.getName());
        return dto;
    }

    public static LessonTypeEntity on(LessonTypeDto lessonTypeDto) {
        LessonTypeEntity entity = new LessonTypeEntity();
        entity.setId(lessonTypeDto.getId());
        entity.setName(lessonTypeDto.getName());
        return entity;
    }
}
