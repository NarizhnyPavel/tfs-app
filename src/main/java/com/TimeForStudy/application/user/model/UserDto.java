package com.TimeForStudy.application.user.model;

import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.domain.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель представления сущности пользователь.
 *
 * @author Velikanov Artyom
 */
@Data
@NoArgsConstructor
public class UserDto {

    /**
     * Идентификатор.
     */
    private Long id;
    /**
     * Номер телефона.
     */
    private String phone;
    /**
     * Имя пользователя.
     */
    private String firstName;
    /**
     * Фамилия пользователя.
     */
    private String lastName;
    /**
     * Отчество пользователя.
     */
    private String patronymic;
    /**
     * Роль пользователя.
     */
    private RoleDto userRole;

    public static UserDto of(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setPhone(user.getPhone());
        UserInfo info = user.getUserInfo();
        dto.setFirstName(info.getFirstName());
        dto.setLastName(info.getLastName());
        dto.setPatronymic(info.getPatronymic());
        dto.setUserRole(RoleDto.of(user.getRole().getId(), user.getRole().getName(),
                user.getRole().getRank()));
        return dto;
    }

}
