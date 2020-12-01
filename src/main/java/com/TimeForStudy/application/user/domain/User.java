package com.TimeForStudy.application.user.domain;

import com.TimeForStudy.application.group.domain.GroupEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Доменная модель <strong>Пользователь</strong>
 *
 * @author Narizhnyj Pavel
 */
@Data
@Entity
@ToString(of = "id")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "user_tb")
public class User {

    /**
     * Идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name="seq_user", sequenceName="SEQ_USER", allocationSize = 1)
    private Long id;
    /**
     * Телефон пользователя.
     */
    @Column(name = "phone")
    private String phone;
    /**
     * Пароль пользователя.
     */
    @Column(name = "password")
    private String password;
    /**
     * Дата последнего редактирования.
     */
    @Column(name = "last_update_date")
    private Date lastUpdateDate;
    /**
     * Информация о пользователе.
     */
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="user_info_id")
    private UserInfo userInfo;
    /**
     * Роль пользователя.
     */
    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private UserRole role;
    /**
     * Группы, в которых состоит пользователь.
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = GroupEntity.class)
    @JoinTable(
            name = "group_user",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id")
    )
    private List<GroupEntity> groups = new ArrayList<>();

}