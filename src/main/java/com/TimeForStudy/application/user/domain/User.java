package com.TimeForStudy.application.user.domain;

import com.TimeForStudy.application.group.domain.Group;
import com.TimeForStudy.application.otherDataClasses.UserRole;
import com.TimeForStudy.application.otherDataClasses.UserRoles;
import com.TimeForStudy.application.user.model.AddUserDto;
import lombok.Data;

import javax.persistence.*;

@Entity @Data @Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name="seq_user", sequenceName="SEQ_USER", allocationSize=1)
    @Column(name = "id")
    private int id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private byte role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public User() {

    }

    public User(String phone, String name, UserRole role) {
        this.phone = phone;
        this.name = name;
        this.role = role.getId();
    }

    public User(String phone, String name, String groupNumber) {
        this.phone = phone;
        this.name = name;
        this.role = UserRoles.STUDENT.getId();
//        this.group =
//        this.group
    }

    public  User(AddUserDto userDto) {
        this.name = userDto.getName();
        this.phone = userDto.getPhone();
        this.role = userDto.getRole();
        this.group = userDto.getGroup();
    }
    public User(String phone, UserRole role) {
        this.phone = phone;
        this.role = role.getId();
    }

    public String getName() {
        return name;
    }
}