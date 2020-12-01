package com.TimeForStudy.application.user.model;

import lombok.Data;

/**
 * Модель регистрации пользователя.
 *
 * @author Velikanov Artyom
 */
@Data
public class UserRegistrationDto {

    /**
     * Пароль.
     */
    private String password;
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
     * Номер телефона.
     */
    private String phoneNumber;

}
