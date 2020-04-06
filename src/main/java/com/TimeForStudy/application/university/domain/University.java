package com.TimeForStudy.application.university.domain;

import com.TimeForStudy.application.semester.domain.Semester;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<Semester> semesters;
}