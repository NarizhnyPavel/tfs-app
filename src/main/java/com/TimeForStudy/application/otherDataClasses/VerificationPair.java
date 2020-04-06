package com.TimeForStudy.application.otherDataClasses;

import lombok.Data;

@Data
public class VerificationPair {
    private String phone;
    private Integer code;

    public VerificationPair(String phone, Integer code) {
        this.phone = phone;
        this.code = code;
    }
}
