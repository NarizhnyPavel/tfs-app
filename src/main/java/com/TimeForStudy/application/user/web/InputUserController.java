package com.TimeForStudy.application.user.web;

import com.TimeForStudy.application.otherDataClasses.VerificationPair;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.LoginUserService;
import com.TimeForStudy.application.user.service.RegistrationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Обработчик запросов авторизации/регистрации.
 *
 * @author Velikanov Artyom.
 * @author Narizhny Pavel.
 */
@RestController
@RequiredArgsConstructor
public class InputUserController {

    /**
     * {@link LoginUserService}
     */
    public final LoginUserService loginUserService;

    /**
     * {@link RegistrationUserService}
     */
    public final RegistrationUserService registrationUserService;

    /**
     * Проверка кода.
     *
     * @param verificationPair пара телефон - код.
     * @return пользователь.
     */
    @PostMapping(value = "login/checkCode")
    public UserDto checkCode(@RequestBody VerificationPair verificationPair) {
        return loginUserService.checkCode(verificationPair);
    }

    /**
     * Проверка телефона.
     *
     * @param phone телефон.
     * @return статус.
     */
    @PostMapping(value = "login/checkPhone")
    public String checkPhone(@RequestBody String phone) {
        return loginUserService.checkPhone(phone);
    }

    /**
     * Авторизация пользователя.
     *
     * @param phone телефон.
     * @return статус.
     */
    @PostMapping(value = "login/login")
    public String loginUser(@RequestBody String phone) {
        return loginUserService.sendCode(phone);
    }

    /**
     * Регистрация нового пользователя.
     *
     * @param addUserDto пользователь.
     * @return статус
     */
    @PostMapping(value = "login/register")
    public String addUser(@RequestBody AddUserDto addUserDto) {
        return  registrationUserService.saveUser(addUserDto);
    }
}
