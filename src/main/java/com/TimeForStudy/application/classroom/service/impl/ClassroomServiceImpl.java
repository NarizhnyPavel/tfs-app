package com.TimeForStudy.application.classroom.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.classroom.domain.ClassroomRepository;
import com.TimeForStudy.application.classroom.service.ClassroomService;
import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.error.ApplicationException;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса учебных помешений.
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    /**
     * {@link ClassroomRepository}
     */
    private final ClassroomRepository classroomRepository;

    /**
     * Получение учебного помещения по идентификатору.
     *
     * @param id идентификатор учебного помещения.
     * @return учебное помещение.
     */
    @Override
    public IdNameDto getClassroomById(Long id) {
        ClassroomEntity classroom = classroomRepository.findById(id)
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        return IdNameDto.of(classroom.getId(), classroom.getNumber());
    }

    /**
     * Создание нового учебного помещения.
     *
     * @param classroomDto модель учебного помещения.
     */
    @Override
    public void saveClassroom(IdNameDto classroomDto) {
        ClassroomEntity classroom = new ClassroomEntity();
        classroom.setNumber(classroomDto.getName());
        classroomRepository.save(classroom);
    }

    /**
     * Редактирование учебного помещения.
     *
     * @param id идентификатор учебного помещения.
     * @param classroom модель учебного помещения.
     */
    @Override
    public void updateClassroom(Long id, IdNameDto classroom) {
        ClassroomEntity updated = classroomRepository.findById(id)
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        if (StringUtils.isNotBlank(classroom.getName())) {
            updated.setNumber(classroom.getName());
        }
        classroomRepository.save(updated);
    }

    /**
     * Удаление учебного помещения.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteClassroom(Long id) {
        ClassroomEntity classroomEntity = classroomRepository.findById(id)
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        if (!CollectionUtils.isEmpty(classroomEntity.getLessons()))
            throw new ApplicationException(ErrorDescription.CLASSROOM_HAS_LESSONS);
        classroomRepository.deleteById(id);
    }

    /**
     * Получение списка всех учебных помещений.
     *
     * @return список учебных помещений.
     */
    @Override
    public List<IdNameDto> findAll() {
        List<ClassroomEntity> classroomEntities = classroomRepository.findAll();
        return classroomEntities.stream()
                .map(it -> IdNameDto.of(it.getId(), it.getNumber()))
                .collect(Collectors.toList());
    }

    /**
     * Получение списка учебных помещений по наименованию.
     *
     * @param name наименование учебного помещения.
     * @return список учебных помещений.
     */
    @Override
    public List<IdNameDto> findAllClassrooms(String name) {
        List<ClassroomEntity> classroomEntities = classroomRepository.findAll();
        List<IdNameDto> lists = new ArrayList<>();
        for (ClassroomEntity classroom : classroomEntities) {
            String cl = String.valueOf(classroom.getNumber());
            if (cl.contains(name)) {
                lists.add(IdNameDto.of(classroom.getId(), cl));
            }
        }
        return lists;
    }
}
