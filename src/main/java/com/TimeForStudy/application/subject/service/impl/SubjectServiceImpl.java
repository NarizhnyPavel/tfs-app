package com.TimeForStudy.application.subject.service.impl;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.subject.domain.SubjectEntity;
import com.TimeForStudy.application.subject.domain.SubjectRepository;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.subject.service.SubjectService;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Реализация сервиса запросов к преподаваемым дисциплинам.
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
     * Получение преподаваемой дисциплины по идентификатору.
     *
     * @param id идентификатор.
     * @return преподаваемая дисциплина
     */
    @Override
    public SubjectDto getSubjectById(Long id) {
        SubjectEntity subject = subjectRepository.findById(id)
                .orElseThrow(ErrorDescription.SUBJECT_NOT_FOUNT::exception);
        return SubjectDto.of(subject.getId(), subject.getName(), subject.getArc());
    }

    /**
     * Добавление преподаваемой дисциплины.
     *
     * @param subject преподаваемая дисциплина.
     */
    @Override
    public void saveSubject(SubjectDto subject) {
        SubjectEntity entity = new SubjectEntity();
        entity.setArc(subject.getArc());
        entity.setName(subject.getName());
        subjectRepository.save(entity);
    }

    /**
     * Редактирование преподаваемой дисциплины.
     *
     * @param id         идентификатор.
     * @param subjectDto преподаваемая дисциплина.
     */
    @Override
    public void updateSubject(Long id, SubjectDto subjectDto) {
        SubjectEntity updated = subjectRepository.findById(id)
                .orElseThrow(ErrorDescription.SUBJECT_NOT_FOUNT::exception);
        if (Objects.nonNull(subjectDto.getName())) {
            updated.setName(subjectDto.getName());
        }
        if (Objects.nonNull(subjectDto.getArc())) {
            updated.setArc(subjectDto.getArc());
        }
        subjectRepository.save(updated);
    }

    /**
     * Удаление преподаваемой дисциплины.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    /**
     * Получение списка преподаваемых дисциплин.
     *
     * @return список преподаваемых дисциплин.
     */
    @Override
    public List<SubjectDto> findAll() {
        return subjectRepository.findAll().stream()
                .map(it -> SubjectDto.of(it.getId(), it.getName(), it.getArc()))
                .collect(Collectors.toList());
    }

    /**
     * Получение дисциплин по наименованию.
     *
     * @param name наименование дисциплины.
     * @return список преподаваемых дисциплин.
     */
    @Override
    public List<IdNameDto> findAllSubjects(String name) {
        return subjectRepository.findAll().stream()
                .filter(it -> it.getName().contains(name))
                .map(subject -> IdNameDto.of(subject.getId(), subject.getName()))
                .collect(Collectors.toList());
    }
}
