package com.TimeForStudy.application.user.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Доменная модель <strong>Персональные данные</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@Table(name = "user_info")
public class UserInfo {

    /**
     * Идентификатор персональных данных.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_info")
    @SequenceGenerator(name="seq_user_info", sequenceName="SEQ_USER_INFO", allocationSize = 1)
    private Long id;
    /**
     * Пользователь.
     */
    @OneToOne (mappedBy = "userInfo")
    private User user;
    /**
     * Имя пользователя.
     */
    @JoinColumn(name = "first_name")
    private String firstName;
    /**
     * Фамилия пользователя.
     */
    @JoinColumn(name = "last_name")
    private String lastName;
    /**
     * Отчество пользователя.
     */
    @JoinColumn(name = "patronymic_name")
    private String patronymic;

    public String getFullName(){
        return this.lastName + " " +  this.firstName + " " + this.patronymic;
    }

}
