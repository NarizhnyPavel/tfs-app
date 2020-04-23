package com.TimeForStudy.application.user.service;

import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.RegisterDto;

/**
 * Сервис регистрации пользователя.
 *
 * @author Velikanov Artyom
 */
public interface RegistrationUserService {

    /**
     *  Проверка и сохранение пользователя
     *
     * @param registerDto регистрация.
     * @return статус
     */
    String saveUser(RegisterDto registerDto);

}