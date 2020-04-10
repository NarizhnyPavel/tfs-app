package com.TimeForStudy.application.semester.service.impl;

import com.TimeForStudy.application.semester.domain.SemesterEntity;
import com.TimeForStudy.application.semester.domain.SemesterRepository;
import com.TimeForStudy.application.semester.model.AddSemesterDto;
import com.TimeForStudy.application.semester.model.SemesterDto;
import com.TimeForStudy.application.semester.service.SemesterService;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.university.model.UniversityDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса CRUD запросов к сущности семестр
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
    private UniversityRepository universityRepository;

    /**
     * Возвращение семестра по идентификатору.
     *
     * @param id идентификатор.
     * @return семестр.
     */
    @Override
    public SemesterDto getSemesterById(long id) {
        SemesterEntity semesterEntity = semesterRepository.findById(id)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        return SemesterDto.of(semesterEntity);
    }

    /**
     * Сохранение семестра.
     *
     * @param addSemesterDto семестр.
     */
    @Override
    public void saveSemester(AddSemesterDto addSemesterDto) {
        universityRepository.findById(addSemesterDto.getUniversity().getId())
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        SemesterEntity semesterEntity = new SemesterEntity(addSemesterDto);
        semesterRepository.save(semesterEntity);
    }

    /**
     * Изменение значений семестра.
     *
     * @param id идентификатор.
     * @param addSemesterDto семестр.
     */
    @Override
    public void updateSemester(long id, AddSemesterDto addSemesterDto) {
        SemesterEntity updated = semesterRepository.findById(id)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        if (addSemesterDto.getUniversity()!=null) {
            universityRepository.findById(addSemesterDto.getUniversity().getId())
                    .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
            updated.setUniversity(UniversityDto.on(addSemesterDto.getUniversity()));
        }
        if (addSemesterDto.getEnd()!=null) {
            updated.setEnd(addSemesterDto.getEnd());
        }
        if (addSemesterDto.getEnd()!=null) {
            updated.setStart(addSemesterDto.getStart());
        }
        semesterRepository.save(updated);
    }

    /**
     * Удаление семестра.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteSemester(long id) {
        semesterRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих семестров.
     *
     * @return список семестров.
     */
    @Override
    public List<SemesterDto> findAll() {
        List<SemesterEntity> semesterEntities = semesterRepository.findAll();
        return semesterEntities.stream().map(SemesterDto::of).collect(Collectors.toList());
    }
}
