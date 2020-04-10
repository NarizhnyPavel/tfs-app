package com.TimeForStudy.application.classroom.model;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import lombok.Data;

/**
 * Модель представления сущности кабинет
 *
 * @author Velikanov Artyom
 */
@Data
public class ClassroomDto {

    private long id;

    /**
     * Номер кабинета
     */
    private int number;

    public static ClassroomDto of(ClassroomEntity classroomEntity) {
        ClassroomDto dto = new ClassroomDto();
        dto.setId(classroomEntity.getId());
        dto.setNumber(classroomEntity.getNumber());
        return dto;
    }

    public static ClassroomEntity on(ClassroomDto classroomDto) {
        ClassroomEntity entity = new ClassroomEntity();
        entity.setId(classroomDto.getId());
        entity.setNumber(classroomDto.getNumber());
        return entity;
    }
}
