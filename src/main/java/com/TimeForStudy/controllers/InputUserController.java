package com.TimeForStudy.controllers;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.repository.UserRepository;
import com.TimeForStudy.service.LoginUserService;
import com.TimeForStudy.service.RegistrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InputUserController {

    public LoginUserService loginUserService;

    public RegistrationUserService registrationUserService;

    public UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRegistrationUserService(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }

    @Autowired
    public void setInputUserService(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    @GetMapping(value = "/login/{phone}")
    public String loginUser(@PathVariable String phone) { return null;
    }

    @PostMapping(value = "/login/")
    public String aUser(@RequestBody String phone) {
        User user = userRepository.findByPhone(phone);
        if (user != null)
            return "Hello, ".concat(user.getName());
        else
            return "null";
    }
}
