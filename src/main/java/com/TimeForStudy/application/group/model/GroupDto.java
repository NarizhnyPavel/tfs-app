package com.TimeForStudy.application.group.model;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.classroom.model.ClassroomDto;
import com.TimeForStudy.application.group.domain.GroupEntity;
import lombok.Data;


/**
 * Модель представления сущности группа
 *
 * @author Velikanov Artyom
 */
@Data
public class GroupDto {

    private long id;

    /**
     * Номер группы
     */
    private String number;


    public static GroupDto of(GroupEntity groupEntity) {
        GroupDto dto = new GroupDto();
        dto.setId(groupEntity.getId());
        dto.setNumber(groupEntity.getNumber());
        return dto;
    }

    public static GroupEntity on(GroupDto groupDto) {
        GroupEntity entity = new GroupEntity();
        entity.setId(groupDto.getId());
        entity.setNumber(groupDto.getNumber());
        return entity;
    }
}
