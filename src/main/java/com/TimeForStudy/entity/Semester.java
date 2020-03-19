package com.TimeForStudy.entity;

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

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Lesson> lessons;

    @OneToOne(fetch = FetchType.LAZY) //TODO переделать тип связи
    @Column(name = "university_id")
    private University university;
}
