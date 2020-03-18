package com.TimeForStudy.entity;

import javax.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    private int ID;
    @OneToMany(mappedBy = "Lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private int IdLesson;

    private int IdSender;
    private int IdReceiver;
    private int lessonPosition;
    private String text;
}
