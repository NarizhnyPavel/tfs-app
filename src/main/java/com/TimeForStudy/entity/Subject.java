package com.TimeForStudy.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity @Data @Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


}
