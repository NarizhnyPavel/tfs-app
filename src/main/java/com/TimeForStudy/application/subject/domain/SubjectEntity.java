package com.TimeForStudy.application.subject.domain;

import com.TimeForStudy.application.subject.model.AddSubjectDto;
import com.TimeForStudy.application.subject.model.SubjectDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность преподаваемой дисциплины
 *
 * @author Velikanov Artyom
 */
@Entity @Data
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "subject")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_subject")
    @SequenceGenerator(name="seq_subject", sequenceName="SEQ_SUBJECT", allocationSize=1)
    @Column(name = "id")
    private long id;

    /**
     * Наименование преподаваемой дисциплины
     */
    @Column(name = "name")
    private String name;

    /**
     * Сокращение
     */
    @Column(name = "arc")
    private String arc;

    public SubjectEntity(AddSubjectDto addSubjectDto) {
        this.name = addSubjectDto.getName();
        this.arc = addSubjectDto.getArc();
    }
}
