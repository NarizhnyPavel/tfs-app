package com.example.TimeForStudy.entity;

import javax.persistence.*;

@Entity
public class Lesson {

    @Id
    @GeneratedValue
    @ManyToOne( fetch = FetchType.LAZY)
    private int ID;
}
