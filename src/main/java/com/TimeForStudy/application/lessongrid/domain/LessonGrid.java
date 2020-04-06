package com.TimeForStudy.application.lessongrid.domain;

import com.TimeForStudy.application.university.domain.University;
import lombok.Data;

import javax.persistence.*;

@Entity @Data @Table(name = "lesson_grid")
public class LessonGrid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lesson_number")
    private int lessonNumber;

    @Column(name = "time")
    private String time;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;
}
