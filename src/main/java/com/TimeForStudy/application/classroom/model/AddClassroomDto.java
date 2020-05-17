package com.TimeForStudy.application.classroom.model;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.model.AddGroupDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * Модель для добавления сущности кабинет
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddClassroomDto {

    /**
     * Номер кабинета
     */
    private int number;

    public AddClassroomDto(int number) {
        this.number = number;
    }

    public static ClassroomEntity on(AddClassroomDto addClassroomDto) {
        ClassroomEntity entity = new ClassroomEntity();
        entity.setNumber(addClassroomDto.getNumber());
        return entity;
    }
}
