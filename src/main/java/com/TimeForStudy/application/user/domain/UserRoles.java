package com.TimeForStudy.application.user.domain;

import lombok.Getter;

/**
 * Модель типов ролей пользователя.
 *
 * @author Velikanov Artyom
 */
@Getter
public enum UserRoles {

    ADMIN(1L, "Администратор", 1),
    TEACHER(2L, "Преподаватель", 2),
    STUDENT(3L, "Студент", 3);

    /**
     * Роль пользователя.
     */
    private UserRole userRole;

    UserRoles(Long id, String userRoleName, Integer rank) {
        this.userRole = new UserRole();
        this.userRole.setId(id);
        this.userRole.setName(userRoleName);
        this.userRole.setRank(rank);
    }

}
