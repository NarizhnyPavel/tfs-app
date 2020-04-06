package com.TimeForStudy.application.notification.domain;

import com.TimeForStudy.application.lesson.domain.Lesson;
import com.TimeForStudy.application.user.domain.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lessons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;
}
