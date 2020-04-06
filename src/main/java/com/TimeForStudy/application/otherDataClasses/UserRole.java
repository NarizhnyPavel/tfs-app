package com.TimeForStudy.application.otherDataClasses;

import com.TimeForStudy.application.user.domain.User;

/**
 * Класс, описывающий роль в системе для {@link User}
 * @author Narizhny Pavel
 * @version 1.0
 */
public class UserRole  {
    /** числовой идентификатор*/
    private byte id;
    /** имя роли*/
    private String name;

    protected UserRole(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserRole() {
    }

    public byte getId() {
        return id;
    }

    public UserRole getUserRole(int index){
        switch (index){
            case 1: return UserRoles.ADMINISTRATOR;
            case 2: return UserRoles.PROFESSOR;
            case 3: return UserRoles.SUPER_STUDENT;
            default: return UserRoles.STUDENT;
        }
    }
}