package com.TimeForStudy.otherDataClasses;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class ListWaiting {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int code;

    public ListWaiting(int code) {
        this.code = code;
    }

    public ListWaiting(int id, int code) {
        this.id = id;
        this.code = code;
    }
}
