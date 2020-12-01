package com.TimeForStudy.application.user.service;

import com.TimeForStudy.application.user.model.AddUserDto;

/**
 * Интерфейс сервиса регистрации пользователя.
 *
 * @author Velikanov Artyom
 */
public interface RegistrationUserService {

    /**
     * Регистрация пользователя.
     *
     * @param registerDto запрос на авторизацию.
     */
    void registerUser(AddUserDto registerDto);

}