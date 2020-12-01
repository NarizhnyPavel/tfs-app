package com.TimeForStudy.application.subject.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Доменная модель <strong>Преподаваямая дисциплина</strong>
 *
 * @author Velikanov Artyom
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "subject")
public class SubjectEntity {

    /**
     * Идентификатор преподаваемой дисциплины.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_subject")
    @SequenceGenerator(name="seq_subject", sequenceName="SEQ_SUBJECT", allocationSize = 1)
    private Long id;
    /**
     * Наименование преподаваемой дисциплины.
     */
    @Column(name = "name")
    private String name;
    /**
     * Сокращение.
     */
    @Column(name = "arc")
    private String arc;

}
