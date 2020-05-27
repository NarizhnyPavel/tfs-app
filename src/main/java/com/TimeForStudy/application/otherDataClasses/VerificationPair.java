package com.TimeForStudy.application.otherDataClasses;

import lombok.Data;

@Data
public class VerificationPair {
    private String phone;
    private String code;

    public VerificationPair(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }
}
