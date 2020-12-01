package com.TimeForStudy.application.user.model;

import lombok.NoArgsConstructor;

/**
 * Модель регистрации.
 *
 * @author Velikanov Artyom.
 */
@NoArgsConstructor
public class RegisterDto  extends AddUserDto{

    /**
     * Группа.
     */
    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
