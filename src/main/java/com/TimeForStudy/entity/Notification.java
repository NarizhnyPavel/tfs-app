package com.TimeForStudy.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Set<Lesson> lessons; //TODO переделать

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "receiver_id")
    private User receiver;
}
