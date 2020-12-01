package com.TimeForStudy.application.group.domain;

import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.user.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Доменная модель <strong>Группа</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "group_tb")
public class GroupEntity {

    /**
     * Идентификатор группы.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_group")
    @SequenceGenerator(name="seq_group", sequenceName="SEQ_GROUP", allocationSize = 1)
    private Long id;
    /**
     * Номер группы.
     */
    @Column(name = "number")
    private String number;
    /**
     *  Лекции, проводимые у данной группы.
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private List<LessonEntity> lessons = new ArrayList<>();
    /**
     *  Студенты, принадлежащие группе.
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private List<User> users = new ArrayList<>();

}
