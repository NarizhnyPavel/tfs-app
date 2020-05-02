package com.TimeForStudy.application.classroom.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.classroom.domain.ClassroomRepository;
import com.TimeForStudy.application.classroom.model.AddClassroomDto;
import com.TimeForStudy.application.classroom.model.ClassroomDto;
import com.TimeForStudy.application.classroom.model.ClassroomsDto;
import com.TimeForStudy.application.classroom.service.ClassroomService;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса CRUD запросов к сущности кабинет
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
     * Возвращение кабинета по идентификатору.
     *
     * @param id идентификатор.
     * @return кабинет.
     */
    @Override
    public ClassroomDto getClassroomById(long id) {
        ClassroomEntity classroomEntity = classroomRepository.findById(id)
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        return ClassroomDto.of(classroomEntity);
    }

    /**
     * Сохранение кабинета.
     *
     * @param addClassroomDto кабинет.
     */
    @Override
    public void saveClassroom(AddClassroomDto addClassroomDto) {
        ClassroomEntity classroomEntity = new ClassroomEntity(addClassroomDto);
        classroomRepository.save(classroomEntity);
    }

    /**
     * Изменение значений кабинета.
     *
     * @param id идентификатор.
     * @param addClassroomDto кабинет.
     */
    @Override
    public void updateClassroom(long id, AddClassroomDto addClassroomDto) {
        ClassroomEntity updated = classroomRepository.findById(id)
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        if (addClassroomDto.getNumber()!=0) {
            updated.setNumber(addClassroomDto.getNumber());
        }
        classroomRepository.save(updated);
    }

    /**
     * Удаление кабинета.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteClassroom(long id) {
        classroomRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих кабинетов.
     *
     * @return список кабинетов.
     */
    @Override
    public List<ClassroomDto> findAll() {
        List<ClassroomEntity> classroomEntities = classroomRepository.findAll();
        return classroomEntities.stream().map(ClassroomDto::of).collect(Collectors.toList());
    }

    /**
     * Возвращение всех существующих кабинетов.
     *
     * @return список кабинетов.
     */
    @Override
    public List<ClassroomsDto> findAllClassrooms(String name) {
        List<ClassroomEntity>  classroomEntities= classroomRepository.findAll();
        List<ClassroomsDto> classroomsDtos = new ArrayList<>();
        for (ClassroomEntity classroom : classroomEntities) {
            String cl = String.valueOf(classroom.getNumber());
            if (cl.contains(name)) {
                classroomsDtos.add(new ClassroomsDto(classroom.getId(), cl));
            }
        }
        return classroomsDtos;
    }
}
