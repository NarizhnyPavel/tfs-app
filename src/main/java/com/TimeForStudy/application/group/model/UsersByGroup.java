package com.TimeForStudy.application.group.model;

import lombok.Data;

@Data
public class UsersByGroup {

    private int number;

    private int role;

    private String name;

    private String phone;

    public UsersByGroup (int number, String name, int role, String phone) {
        this.name = name;
        this.number = number;
        this.role = role;
        this.phone = phone;
    }
}
