package com.TimeForStudy.entity;

import javax.persistence.*;

@Entity
public class Subject {
    @Id
    @GeneratedValue
    private int ID;
    private String name;
}
