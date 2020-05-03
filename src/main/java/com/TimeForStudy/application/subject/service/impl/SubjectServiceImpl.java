package com.TimeForStudy.application.subject.service.impl;

import com.TimeForStudy.application.subject.domain.SubjectEntity;
import com.TimeForStudy.application.subject.domain.SubjectRepository;
import com.TimeForStudy.application.subject.model.AddSubjectDto;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.subject.model.SubjectsDto;
import com.TimeForStudy.application.subject.service.SubjectService;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        SubjectEntity subjectEntity = subjectRepository.findById(id)
                .orElseThrow(ErrorDescription.SUBJECT_NOT_FOUNT::exception);
        return SubjectDto.of(subjectEntity);
    }

    /**
     * Сохранение преподаваемой дисциплины.
     *
     * @param addSubjectDto преподаваемая дисциплина.
     */
    @Override
    public void saveSubject(AddSubjectDto addSubjectDto) {
        SubjectEntity subjectEntity = new SubjectEntity(addSubjectDto);
        subjectRepository.save(subjectEntity);
    }

    /**
     * Изменение значений преподаваемой дисциплины.
     *
     * @param id идентификатор.
     * @param addSubjectDto преподаваемая дисциплина.
     */
    @Override
    public void updateSubject(long id, AddSubjectDto addSubjectDto) {
        SubjectEntity updated = subjectRepository.findById(id)
                .orElseThrow(ErrorDescription.SUBJECT_NOT_FOUNT::exception);
        if (addSubjectDto.getName()!=null) {
            updated.setName(addSubjectDto.getName());
        }
        if (addSubjectDto.getArc()!=null) {
            updated.setArc(addSubjectDto.getArc());
        }
        subjectRepository.save(updated);
    }

    /**
     * Удаление преподаваемой дисциплины.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteSubject(long id) {
        subjectRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих преподаваемых дисциплин.
     *
     * @return список преподаваемый дисциплин.
     */
    @Override
    public List<SubjectDto> findAll() {
        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        return subjectEntities.stream().map(SubjectDto::of).collect(Collectors.toList());
    }

    /**
     * Возвращение всех существующих преподаваемых дисциплин.
     *
     * @return список преподаваемый дисциплин.
     */
    @Override
    public List<SubjectsDto> findAllSubjects(String name) {

        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        List<SubjectsDto> subjectsDtos = new ArrayList<>();
        for (SubjectEntity subject : subjectEntities) {
            if (subject.getName().contains(name)) {
                subjectsDtos.add(new SubjectsDto(subject.getId(), subject.getName()));
            }
        }
        return subjectsDtos;
    }
}
