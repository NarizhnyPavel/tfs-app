package com.example.TimeForStudy.entity;

import javax.persistence.*;

@Entity
public class LessonGrid {

    @Id
    @GeneratedValue
    private int ID;
    @OneToMany(mappedBy = "University", cascade = CascadeType.ALL, orphanRemoval = true)
    private int IdUniversity;
    private int lessonNumber;
    private String time;

    public LessonGrid() {
    }
}
