package com.TimeForStudy.application.user.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * Доменная модель <strong>Роль пользователя</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@Table(name = "role")
public class UserRole {

    /**
     * Идентификатор роли пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_role")
    @SequenceGenerator(name="seq_user_role", sequenceName="SEQ_USER_ROLE", allocationSize = 1)
    private Long id;
    /**
     * Наименование роли.
     */
    @JoinColumn(name = "name")
    private String name;
    /**
     * Ранг роли.
     */
    private Integer rank;
    /**
     * Список пользователей.
     */
    @OneToMany(mappedBy="role", fetch = FetchType.LAZY)
    private List<User> users;

}
