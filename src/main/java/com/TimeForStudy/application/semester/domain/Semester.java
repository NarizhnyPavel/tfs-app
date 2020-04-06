package com.TimeForStudy.application.semester.domain;

import com.TimeForStudy.application.lesson.domain.Lesson;
import com.TimeForStudy.application.university.domain.University;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity @Data @Table(name = "semester")
public class Semester {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "start")
    private Date start;

    @Column(name = "end")
    private Date end;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY)
    private Set<Lesson> lessons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;
}
