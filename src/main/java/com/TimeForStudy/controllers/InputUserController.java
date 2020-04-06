package com.TimeForStudy.controllers;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.otherDataClasses.VerificationPair;
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
     * Check sent code
     */
    @PostMapping(value = "/checkCode")
    public String checkCode(@RequestBody VerificationPair verificationPair) {
        return loginUserService.checkCode(verificationPair);
    }
    /**
    * Check phone for being registered
     */
    @PostMapping(value = "/checkPhone")
    public String checkPhone(@RequestBody String phone) {
        return loginUserService.checkPhone(phone);
    }

    /**
     * Login by phone by sending sms code
     */
    @PostMapping(value = "/login")
    public String loginUser(@RequestBody String phone) {
        return loginUserService.sendCode(phone);
    }

    /**
     * Register new user
     */
    @PostMapping(value = "/register")
    public String addUser(@RequestBody User user) {
        return  registrationUserService.saveUser(user);
    }
}
