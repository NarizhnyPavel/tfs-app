package com.TimeForStudy.application.user.service;


import com.TimeForStudy.application.otherDataClasses.VerificationPair;
import com.TimeForStudy.application.user.model.UserDto;

public interface LoginUserService {

    String sendCode(String phone);

    String checkPhone(String phone);

    UserDto checkCode(VerificationPair verificationPair);
}
