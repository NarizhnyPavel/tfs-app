package com.TimeForStudy.application.user.service;

import com.TimeForStudy.application.user.model.AddUserDto;

/**
 * Сервис регистрации пользователя.
 *
 * @author Velikanov Artyom
 */
public interface RegistrationUserService {

    /**
     *  Проверка и сохранение пользователя
     *
     * @param addUserDto
     * @return статус
     */
    String saveUser(AddUserDto addUserDto);

}