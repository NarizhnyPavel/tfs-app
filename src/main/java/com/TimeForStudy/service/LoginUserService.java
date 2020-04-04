package com.TimeForStudy.service;


import com.TimeForStudy.otherDataClasses.VerificationPair;

public interface LoginUserService {

    public String sendCode(String phone);

    public String checkPhone(String phone);

    public String checkCode(VerificationPair verificationPair);
}
