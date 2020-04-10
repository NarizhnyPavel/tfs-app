package com.TimeForStudy.application.subject.service.impl;

import com.TimeForStudy.application.subject.domain.SubjectRepository;
import com.TimeForStudy.application.subject.model.AddSubjectDto;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса CRUD запросов к сущности преподаваемая дисциплина
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    /**
     * {@link SubjectRepository}
     */
    private final SubjectRepository subjectRepository;

    /**
     * Возвращение преподаваемой дисциплины по идентификатору.
     *
     * @param id идентификатор.
     * @return преподаваемая дисциплина.
     */
    @Override
    public SubjectDto getSubjectById(long id) {
        return null;
    }

    /**
     * Сохранение преподаваемой дисциплины.
     *
     * @param addSubjectDto преподаваемая дисциплина.
     */
    @Override
    public void saveSubject(AddSubjectDto addSubjectDto) {

    }

    /**
     * Изменение значений преподаваемой дисциплины.
     *
     * @param id идентификатор.
     * @param addSubjectDto преподаваемая дисциплина.
     */
    @Override
    public void updateSubject(long id, AddSubjectDto addSubjectDto) {

    }

    /**
     * Удаление преподаваемой дисциплины.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteSubject(long id) {

    }

    /**
     * Возвращение всех существующих преподаваемых дисциплин.
     *
     * @return список преподаваемый дисциплин.
     */
    @Override
    public List<SubjectDto> findAll() {
        return null;
    }
}
