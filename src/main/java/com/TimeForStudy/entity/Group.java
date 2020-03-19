package com.TimeForStudy.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private String number;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Lesson> lessons;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group-user",
            joinColumns = { @JoinColumn(name = "group_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") })
    private Set<User> users = new HashSet<User>();
}
