package com.TimeForStudy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity @Data @Table(name = "university")
public class University {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "weeks")
    private int weeks;

    @Column(name = "work_days")
    private String workDays;

    @Column(name = "lesson_duration")
    private int lessonDuration;


}
