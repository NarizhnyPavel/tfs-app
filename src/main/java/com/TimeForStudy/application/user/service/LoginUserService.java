package com.TimeForStudy.application.user.service;


import com.TimeForStudy.application.otherDataClasses.VerificationPair;
import com.TimeForStudy.application.user.model.UserDto;

/**
 * Сервис авторизации пользователя.
 *
 * @author Velikanov Artyom
 * @author Narizhny Pavel
 */
public interface LoginUserService {

    /**
     * Проверка телефона
     *
     * @param phone телефон
     * @return статус
     */
    String sendCode(String phone);

    /**
     * Проверка телефона
     *
     * @param phone телефон
     * @return статус
     */
    String checkPhone(String phone);

    /**
     * Проверка кода
     *
     * @param verificationPair пара код - телефон
     * @return пользователь
     */
    UserDto checkCode(VerificationPair verificationPair);
}
