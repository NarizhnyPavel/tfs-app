package com.TimeForStudy.application.group.domain;

import com.TimeForStudy.application.group.model.AddGroupDto;
import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.user.domain.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность группы
 *
 * @author Velikanov Artyom
 */
@Entity @Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "group")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_group")
    @SequenceGenerator(name="seq_group", sequenceName="SEQ_GROUP", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     * Номер группы
     */
    @Column(name = "number")
    private String number;

    /**
     *  Лекции, проводимые у данной группы
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private List<LessonEntity> lessons = new ArrayList<>();

    /**
     *  Студенты, принадлежащие группе
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private List<UserEntity> users = new ArrayList<>();

    public  GroupEntity(AddGroupDto addGroupDto) {
        this.number = addGroupDto.getNumber();
    }
}
