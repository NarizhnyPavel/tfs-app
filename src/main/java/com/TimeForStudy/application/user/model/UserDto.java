package com.TimeForStudy.application.user.model;

import com.TimeForStudy.application.user.domain.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Модель представления сущности пользователь
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class UserDto {

    private long id;

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

    public UserDto(String phone, String name, byte role) {
        this.phone = phone;
        this.name = name;
        this.role = role;
    }

    public static UserDto of(UserEntity user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setPhone(user.getPhone());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        return dto;
    }

    public static  UserEntity on(UserDto userDto) {
        UserEntity entity = new UserEntity();
        entity.setId(userDto.getId());
        entity.setPhone(userDto.getPhone());
        entity.setName(userDto.getName());
        entity.setRole(userDto.getRole());
        return  entity;
    }
}
