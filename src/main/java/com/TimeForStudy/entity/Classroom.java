package com.TimeForStudy.entity;

import lombok.*;

import javax.persistence.*;

@Entity @Data @Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private int number;

}
