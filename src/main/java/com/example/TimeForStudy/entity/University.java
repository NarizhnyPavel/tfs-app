package com.example.TimeForStudy.entity;

import javax.persistence.*;

@Entity
public class University {

    @Id
    @GeneratedValue()
    @ManyToOne( fetch = FetchType.LAZY)
    private int ID;
    private String name;
    private int lessonDuration;

    public University() {
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLessonDuration() {
        return lessonDuration;
    }

    public void setLessonDuration(int lessonDuration) {
        this.lessonDuration = lessonDuration;
    }
}
