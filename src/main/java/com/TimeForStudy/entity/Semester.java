package com.TimeForStudy.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Semester {

    @Id
    @GeneratedValue
    private int ID;
    @OneToMany(mappedBy = "University", cascade = CascadeType.ALL, orphanRemoval = true)
    private int IdUniversity;
    private Date start;
    private Date end;

    public Semester() {
    }
}
