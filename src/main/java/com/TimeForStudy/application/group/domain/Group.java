package com.TimeForStudy.application.group.domain;

import com.TimeForStudy.application.user.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_group")
    @SequenceGenerator(name="seq_group", sequenceName="SEQ_GROUP", allocationSize=1)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private String number;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;


}
