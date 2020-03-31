package com.TimeForStudy.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity @Data @Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    private Set<Lesson> lessons;

}
