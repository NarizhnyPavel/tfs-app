package com.TimeForStudy.application.user.model;

import com.TimeForStudy.application.group.model.GroupsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Модель для добавления сущности пользователь.
 *
 * @author Velikanov Artyom
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {

    /**
     * Номер телефона.
     */
    private String phone;
    /**
     * Пароль.
     */
    private String password;
    /**
     * Имя.
     */
    private String firstName;
    /**
     * Фамилия.
     */
    private String lastName;
    /**
     * Отчество.
     */
    private String patronymic;
    /**
     * Группы.
     */
    private List<GroupsDto> groups;

}
