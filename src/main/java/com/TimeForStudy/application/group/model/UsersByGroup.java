package com.TimeForStudy.application.group.model;

import lombok.Data;

@Data
public class UsersByGroup {

    private int number;

    private int role;

    private String name;

    public UsersByGroup (int number, String name, int role) {
        this.name = name;
        this.number = number;
        this.role = role;
    }
}
