package com.TimeForStudy.entity;

import javax.persistence.*;

@Entity
public class Lesson {

    @Id
    @GeneratedValue
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn()
    private int ID;
}
