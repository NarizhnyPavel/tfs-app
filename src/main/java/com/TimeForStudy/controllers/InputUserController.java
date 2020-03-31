package com.TimeForStudy.controllers;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.otherDataClasses.ListWaiting;
import com.TimeForStudy.service.LoginUserService;
import com.TimeForStudy.service.RegistrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InputUserController {

    public LoginUserService loginUserService;

    public RegistrationUserService registrationUserService;


    @Autowired
    public void setLoginUserService(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    @Autowired
    public void setRegistrationUserService(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }

    /**
     * проверка кода
     */

    @PostMapping(value = "/code/")
    public String checkCode(@RequestBody Integer id, @RequestBody Integer code) {
        return loginUserService.CheckCode(new ListWaiting(id, code));
    }

    /**
     * вход по телефону
     */
    @PostMapping(value = "/login/")
    public String loginUser(@RequestBody String phone) {
        return loginUserService.CheckPhone(phone);
    }

    /**
     * регистрация пользователя
     */
    @PostMapping(value = "/register/")
    public String addUser(@RequestBody User user) {
        return  registrationUserService.saveUser(user);
    }
}
