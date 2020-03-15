package com.example.TimeForStudy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student extends User {
    @Id
    private String phone;
    private String name;
    private String surname;

    public Student() {
    }

    public Student(String phone) {
        this.phone = phone;
    }

    public Student(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public Student(String phone, String name, String surname) {
        this.phone = phone;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
