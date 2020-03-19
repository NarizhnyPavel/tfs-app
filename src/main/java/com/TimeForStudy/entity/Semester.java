package com.TimeForStudy.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

}
