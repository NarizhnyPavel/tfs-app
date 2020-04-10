package com.TimeForStudy.application.user.web;

import com.TimeForStudy.application.otherDataClasses.VerificationPair;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.LoginUserService;
import com.TimeForStudy.application.user.service.RegistrationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * Check sent code
     */
    @PostMapping(value = "login/checkCode")
    public UserDto checkCode(@RequestBody VerificationPair verificationPair) {
        return loginUserService.checkCode(verificationPair);
    }
    /**
    * Check phone for being registered
     */
    @PostMapping(value = "login/checkPhone")
    public String checkPhone(@RequestBody String phone) {
        return loginUserService.checkPhone(phone);
    }

    /**
     * Login by phone by sending sms code
     */
    @PostMapping(value = "login/login")
    public String loginUser(@RequestBody String phone) {
        return loginUserService.sendCode(phone);
    }

    /**
     * Register new user
     */
    @PostMapping(value = "login/register")
    public String addUser(@RequestBody AddUserDto addUserDto) {
        return  registrationUserService.saveUser(addUserDto);
    }
}
