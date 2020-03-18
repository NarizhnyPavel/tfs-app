package com.TimeForStudy.entity;

import com.TimeForStudy.otherDataClasses.UserRole;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class User {
    @Id
    @GeneratedValue
    @JoinColumn(name = "id")
    private String id;
    @JoinColumn(name = "phone")
    private String phone;
    @JoinColumn(name = "name")
    private String name;
    @JoinColumn(name = "role")
    private byte role;

    public User() {
        phone = "";
    }

    public User(String phone, String name, UserRole role) {
        this.phone = phone;
        this.name = name;
        this.role = role.getId();
    }

    public User(String phone, UserRole role) {
        this.phone = phone;
        this.role = role.getId();
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }
}
