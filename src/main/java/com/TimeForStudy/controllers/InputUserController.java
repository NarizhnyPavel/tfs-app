package com.TimeForStudy.controllers;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.service.LoginUserService;
import com.TimeForStudy.service.RegistrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InputUserController {

    public LoginUserService loginUserService;

    public RegistrationUserService registrationUserService;

    @Autowired
    public void setRegistrationUserService(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }

    @Autowired
    public void setInputUserService(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    @GetMapping(value = "/login/{phone}")
    public String loginUser(@PathVariable String phone) {
        return null;
    }

    @PostMapping(value = "/login/")
    public void aUser(@RequestBody User user) {

    }
}
