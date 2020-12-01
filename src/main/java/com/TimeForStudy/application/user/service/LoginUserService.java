package com.TimeForStudy.application.user.service;


import com.TimeForStudy.application.user.model.AuthPair;
import com.TimeForStudy.application.user.model.AuthResponce;
import org.springframework.http.ResponseEntity;

/**
 * Интерфейс сервиса авторизации пользователя.
 *
 * @author Velikanov Artyom
 * @author Narizhny Pavel
 */
public interface LoginUserService {

    /**
     * Авторизация и получение токена доступа
     *
     * @param authPair пара телефон - пароль.
     * @return токен доступа.
     */
    ResponseEntity<AuthResponce> authenticate(AuthPair authPair);

}
