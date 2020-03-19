package com.TimeForStudy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity @Data @Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "professor_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "semester_id")
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "lesson_type_id")
    private LessonType lessonType;


}
