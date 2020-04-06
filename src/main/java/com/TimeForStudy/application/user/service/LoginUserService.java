package com.TimeForStudy.application.user.service;


import com.TimeForStudy.application.otherDataClasses.VerificationPair;

public interface LoginUserService {

    public String sendCode(String phone);

    public String checkPhone(String phone);

    public String checkCode(VerificationPair verificationPair);
}
