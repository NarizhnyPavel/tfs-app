package com.TimeForStudy.application.user.web;

import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.AuthPair;
import com.TimeForStudy.application.user.model.AuthResponce;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.LoginUserService;
import com.TimeForStudy.application.user.service.RegistrationUserService;
import com.TimeForStudy.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Обработчик запросов авторизации/регистрации.
 *
 * @author Velikanov Artyom.
 * @author Narizhny Pavel.
 */
@RestController
@RequiredArgsConstructor
public class Authentication {

    /**
     * {@link LoginUserService}
     */
    public final LoginUserService loginUserService;

    /**
     * {@link RegistrationUserService}
     */
    public final RegistrationUserService registrationUserService;

    private final UserUtils userUtils;

    /**
     * Авторизация и получение токена доступа
     *
     * @param authPair пара телефон - пароль.
     * @return токен доступа.
     */
    @PostMapping(value = "/auth")
    public ResponseEntity<AuthResponce> authenticate(@RequestBody AuthPair authPair) {
        return loginUserService.authenticate(authPair);
    }

    /**
     * Регистрация пользователя.
     *
     * @param userDto запрос на авторизацию.
     */
    @PostMapping(value = "/register")
    public void registerUser(@RequestBody AddUserDto userDto){
        registrationUserService.registerUser(userDto);
    }

    /**
     * Получение информации о текущем авторизованном пользователе.
     *
     * @return информации о текущем авторизованном пользователе.
     */
    @GetMapping(value = "/auth/current")
    public UserDto getCurUser(){
        return UserDto.of(userUtils.getCurrentUser());
    }
}
