package com.TimeForStudy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity @Data @Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

}
