package com.TimeForStudy.application.group.domain;

import com.TimeForStudy.application.user.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_group")
    @SequenceGenerator(name="seq_group", sequenceName="SEQ_GROUP", allocationSize=1)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private String number;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<User> users;

}
