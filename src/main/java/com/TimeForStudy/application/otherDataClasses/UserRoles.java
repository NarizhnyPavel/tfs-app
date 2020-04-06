package com.TimeForStudy.application.otherDataClasses;

import com.TimeForStudy.application.user.domain.User;

/**
 * Интерфейс, опиывающий список ролей пользователей
 * @see {@link User}
 * @author Narizhny Pavel
 * @version 1.0
 */
public interface UserRoles {
    UserRole ADMINISTRATOR = new UserRole((byte) 1, "Administrator");
    UserRole PROFESSOR = new UserRole((byte) 2, "Professor");
    UserRole SUPER_STUDENT = new UserRole((byte) 3, "SuperStudent");
    UserRole STUDENT = new UserRole((byte) 4, "Student");
}