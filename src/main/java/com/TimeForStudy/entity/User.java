package com.TimeForStudy.entity;

import com.TimeForStudy.otherDataClasses.UserRole;
import lombok.Data;
import javax.persistence.*;

@Entity @Data @Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private byte role;

    public User(String phone, String name, UserRole role) {
        this.phone = phone;
        this.name = name;
        this.role = role.getId();
    }

    public User(String phone, UserRole role) {
        this.phone = phone;
        this.role = role.getId();
    }
}