package com.TimeForStudy.application.group.model;

import lombok.Data;

@Data
public class UsersByGroup {

    private int number;

    private String name;

    public UsersByGroup (int number, String name) {
        this.name = name;
        this.number = number;
    }
}
