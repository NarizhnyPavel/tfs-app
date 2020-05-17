package com.TimeForStudy.application.group.model;

import com.TimeForStudy.application.group.domain.GroupEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Модель для добавления сущности группа
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddGroupDto {

    /**
     * Номер группы
     */
    private String number;

    public AddGroupDto(String number) {
        this.number = number;
    }

    public static GroupEntity on(AddGroupDto addGroupDto) {
        GroupEntity entity = new GroupEntity();
        entity.setNumber(addGroupDto.getNumber());
        return entity;
    }
}
