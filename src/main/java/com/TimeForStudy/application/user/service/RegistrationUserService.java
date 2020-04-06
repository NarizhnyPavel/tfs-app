package com.TimeForStudy.application.user.service;

import com.TimeForStudy.application.user.model.AddUserDto;

public interface RegistrationUserService {

    String saveUser(AddUserDto addUserDto);

}