package com.TimeForStudy.entity;

import com.TimeForStudy.otherDataClasses.UserRole;
import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @Data @Table(name = "usr")
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



    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "group_id")
    private Group group;

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