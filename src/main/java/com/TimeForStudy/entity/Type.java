package com.TimeForStudy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Type {
    @Id
    @GeneratedValue
    private int ID;
    private String name;
}
