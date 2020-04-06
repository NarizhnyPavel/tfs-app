package com.TimeForStudy.application.lessontype.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity @Data @Table(name = "lesson_type")
public class LessonType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


}