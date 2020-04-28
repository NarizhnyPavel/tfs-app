package com.TimeForStudy.application.user.model;

import com.TimeForStudy.application.group.model.GroupDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Модель регистрации.
 *
 * @author Velikanov Artyom.
 */
@Data
@NoArgsConstructor
public class RegisterDto {

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
     * Группа
     */
    private String group;
}
