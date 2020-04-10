package com.TimeForStudy.application.university.service.impl;

import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.university.model.AddUniversityDto;
import com.TimeForStudy.application.university.model.UniversityDto;
import com.TimeForStudy.application.university.service.UniversityService;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса CRUD запросов к сущности учебное заведение
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    /**
     * {@link UniversityRepository}
     */
    private final UniversityRepository universityRepository;

    /**
     * Возвращение учебного заведения по идентификатору.
     *
     * @param id идентификатор.
     * @return учебное заведение.
     */
    @Override
    public UniversityDto getUniversityById(long id) {
        UniversityEntity universityEntity = universityRepository.findById(id)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        return UniversityDto.of(universityEntity);
    }

    /**
     * Сохранение учебного заведения.
     *
     * @param addUniversityDto учебное заведение.
     */
    @Override
    public void saveUniversity(AddUniversityDto addUniversityDto) {
        UniversityEntity universityEntity = new UniversityEntity(addUniversityDto);
        universityRepository.save(universityEntity);
    }

    /**
     * Изменение значений учебного заведения.
     *
     * @param id идентификатор.
     * @param addUniversityDto учебное заведение.
     */
    @Override
    public void updateUniversity(long id, AddUniversityDto addUniversityDto) {
        UniversityEntity updated = universityRepository.findById(id)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
        if (addUniversityDto.getName()!=null) {
            updated.setName(addUniversityDto.getName());
        }
        if (addUniversityDto.getLessonDuration()!=0) {
            updated.setLessonDuration(addUniversityDto.getLessonDuration());
        }
        if (addUniversityDto.getWeeks()!=0) {
            updated.setWeeks(addUniversityDto.getWeeks());
        }
        if (addUniversityDto.getWorkDays()!=null) {
            updated.setWorkDays(addUniversityDto.getWorkDays());
        }
        universityRepository.save(updated);
    }

    /**
     * Удаление учебного заведения.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteUniversity(long id) {
        universityRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих учебных заведений.
     *
     * @return список учебных заведений.
     */
    @Override
    public List<UniversityDto> findAll() {
        List<UniversityEntity> universityEntities = universityRepository.findAll();
        return universityEntities.stream().map(UniversityDto::of).collect(Collectors.toList());
    }
}
