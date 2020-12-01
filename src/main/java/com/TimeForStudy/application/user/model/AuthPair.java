package com.TimeForStudy.application.user.model;

import lombok.Data;

/**
 * Модель авторизации.
 *
 * @author Velikanov Artyom
 */
@Data
public class AuthPair {

    /**
     * Номер телефона.
     */
    private String phone;
    /**
     * Пароль.
     */
    private String password;
}
