package com.TimeForStudy.application.lesson.model;

import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.lesson.domain.LessonEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Модель представления сущности занятие
 *
 * @author Velikanov Artyom
 */
@Data
@RequiredArgsConstructor
public class LessonDto {

    private long id;

    /**
     * Статус
     */
    private boolean status;

    public static LessonDto of(LessonEntity groupEntity) {
        LessonDto dto = new LessonDto();
        dto.setId(groupEntity.getId());
        dto.setStatus(groupEntity.isStatus());
        return dto;
    }

    public static LessonEntity on(LessonDto lessonDto) {
        LessonEntity entity = new LessonEntity();
        entity.setId(lessonDto.getId());
        entity.setStatus(lessonDto.isStatus());
        return entity;
    }
}
