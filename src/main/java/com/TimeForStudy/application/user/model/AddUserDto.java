package com.TimeForStudy.application.user.model;

import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.user.domain.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Модель для добавления сущности пользователь
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class AddUserDto {

    /**
     * Номер телефона
     */
    private String phone;

    /**
     * Фамилия Имя Отчество
     */
    private String name;

    /**
     * Роль (1 - Диспетчер, 2 - Преподаватель, 3 - Староста, 4 - Студент)
     */
    private byte role;

    /**
     * Группы
     */
    private List<GroupDto> groups;

    public AddUserDto(String phone, String name, byte role) {
        this.phone = phone;
        this.name = name;
        this.role = role;
    }

    public static UserEntity on(AddUserDto addUserDto) {
        UserEntity entity = new UserEntity();
        entity.setPhone(addUserDto.getPhone());
        entity.setName(addUserDto.getName());
        entity.setRole(addUserDto.getRole());
        return  entity;
    }
}
