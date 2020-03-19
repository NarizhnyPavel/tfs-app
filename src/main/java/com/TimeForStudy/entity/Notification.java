package com.TimeForStudy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity @Data @Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "lesson_position")
    private int lessonPosition;

    @Column(name = "text")
    private String text;
}
