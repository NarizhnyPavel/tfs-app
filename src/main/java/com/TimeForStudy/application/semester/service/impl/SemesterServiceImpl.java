package com.TimeForStudy.application.semester.service.impl;

import com.TimeForStudy.application.semester.domain.SemesterEntity;
import com.TimeForStudy.application.semester.domain.SemesterRepository;
import com.TimeForStudy.application.semester.model.SemesterDto;
import com.TimeForStudy.application.semester.service.SemesterService;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Реализация сервиса запросов к семестрам.
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService {

    /**
     * {@link SemesterRepository}
     */
    private final SemesterRepository semesterRepository;
    /**
     * {@link UniversityRepository}
     */
    private final UniversityRepository universityRepository;

    /**
     * Получение семестра по идентификатору.
     *
     * @param id идентификатор.
     * @return семестр.
     */
    @Override
    public SemesterDto getSemesterById(Long id) {
        SemesterEntity entity = semesterRepository.findById(id)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        return SemesterDto.of(entity.getId(), entity.getStart(), entity.getEnd());
    }

    /**
     * Добавление семестра.
     *
     * @param semester семестр.
     */
    @Override
    public void saveSemester(SemesterDto semester) {
        SemesterEntity entity = SemesterEntity.of(null, semester.getStartDate(),
                semester.getEndDate(), getCurrentUniversity(), null);
        semesterRepository.save(entity);
    }

    /**
     * Редактирование семестра.
     *
     * @param id          идентификатор.
     * @param semesterDto семестр.
     */
    @Override
    public void updateSemester(Long id, SemesterDto semesterDto) {
        SemesterEntity updated = semesterRepository.findById(id)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        if (Objects.nonNull(semesterDto.getEndDate())) {
            updated.setEnd(semesterDto.getEndDate());
        }
        if (Objects.nonNull(semesterDto.getStartDate())) {
            updated.setStart(semesterDto.getStartDate());
        }
        semesterRepository.save(updated);
    }

    /**
     * Удаление семестра.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteSemester(Long id) {
        semesterRepository.deleteById(id);
    }

    /**
     * Получение списка семестров.
     *
     * @return список семестров.
     */
    @Override
    public List<SemesterDto> findAll() {
        return semesterRepository.findAll().stream()
                .map(it -> SemesterDto.of(it.getId(), it.getStart(), it.getEnd()))
                .collect(Collectors.toList());
    }

    private UniversityEntity getCurrentUniversity() {
        return universityRepository.findById(1L)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
    }
}
