package com.TimeForStudy.otherDataClasses;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class ListWaiting {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int code;

    public ListWaiting() {
    }
}
