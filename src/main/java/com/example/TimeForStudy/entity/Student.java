package com.example.TimeForStudy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student extends User{
    @Id
    private String phone;
    private String name;
    private String surname;

}
