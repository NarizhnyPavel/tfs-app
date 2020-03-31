package com.TimeForStudy.service;


import com.TimeForStudy.otherDataClasses.VerificationPair;

public interface LoginUserService {

    public String CheckPhone(String phone);

    public String CheckCode(VerificationPair verificationPair);
}
