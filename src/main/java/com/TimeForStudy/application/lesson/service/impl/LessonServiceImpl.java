package com.TimeForStudy.application.lesson.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomRepository;
import com.TimeForStudy.application.classroom.model.ClassroomDto;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lesson.domain.LessonRepository;
import com.TimeForStudy.application.lesson.model.*;
import com.TimeForStudy.application.lesson.service.LessonService;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionRepository;
import com.TimeForStudy.application.lessontype.domain.LessonTypeRepository;
import com.TimeForStudy.application.lessontype.model.LessonTypeDto;
import com.TimeForStudy.application.semester.domain.SemesterRepository;
import com.TimeForStudy.application.semester.model.SemesterDto;
import com.TimeForStudy.application.subject.domain.SubjectRepository;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса CRUD запросов к сущности занятие
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    /**
     * {@link LessonRepository}
     */
    private final LessonRepository lessonRepository;

    /**
     * {@link ClassroomRepository}
     */
    private final ClassroomRepository  classroomRepository;

    /**
     * {@link SubjectRepository}
     */
    private final SubjectRepository subjectRepository;

    /**
     * {@link GroupRepository}
     */
    private final GroupRepository groupRepository;

    /**
     * {@link UserRepository}
     */
    private final UserRepository userRepository;

    /**
     * {@link SemesterRepository}
     */
    private final SemesterRepository semesterRepository;

    /**
     * {@link LessonTypeRepository}
     */
    private final LessonTypeRepository lessonTypeRepository;

    /**
     * {@link LessonPositionRepository}
     */
    private final LessonPositionRepository lessonPositionRepository;

    /**
     * Возвращение занятия по идентификатору.
     *
     * @param id идентификатор.
     * @return занятие.
     */
    @Override
    public LessonDto getLessonById(long id) {
        LessonEntity lessonEntity = lessonRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_NOT_FOUNT::exception);
        return LessonDto.of(lessonEntity);
    }

    /**
     * Возвращение расписания занятий на день
     *
     * @param addInfoLessonDto информация о расписании.
     * @return
     */
    @Override
    public DaysDto getLessonInfo(AddInfoLessonDto addInfoLessonDto) {
        //User
        UserEntity userEntity = userRepository.findById(addInfoLessonDto.getUserId())
                .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        //Группы
        List<GroupEntity> groupEntities = userEntity.getGroups();
        // Занятия в этот день
        List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository
                .findAllByPosition(addInfoLessonDto.getWeekNum());
        // Расписание для вывода
        List<InfoLessonDto> infoLessonDtos = new ArrayList<>();

        for (LessonPositionEntity less : lessonPositionEntities) {
            LessonEntity lessonEntity = less.getLesson();
//            if (lessonEntity.getGroups().retainAll(groupEntities)) {
                InfoLessonDto infoLessonDto = new InfoLessonDto();
                infoLessonDto.setTime("Пока не определено");
                infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                infoLessonDto.setStatus(lessonEntity.isStatus());
                infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                infoLessonDtos.add(infoLessonDto);
//            }
        }

        DaysDto daysDto = new DaysDto();
        daysDto.setDayName("Такой-то день");
        daysDto.setInfoLessonDtos(infoLessonDtos);
        return daysDto;
    }

    /**
     * Сохранение занятия.
     *
     * @param addLessonDto занятие.
     */
    @Override
    public void saveLesson(AddLessonDto addLessonDto) {
        classroomRepository.findById(addLessonDto.getClassroom().getId())
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        subjectRepository.findById(addLessonDto.getSubject().getId())
                .orElseThrow(ErrorDescription.SUBJECT_NOT_FOUNT::exception);
        UserEntity userEntity = userRepository.findById(addLessonDto.getUser().getId())
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        ErrorDescription.ACCESS_IS_DENIED
                .throwIfFalse(userEntity.getRole()==2);
        semesterRepository.findById(addLessonDto.getSemester().getId())
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        lessonTypeRepository.findById(addLessonDto.getLessonType().getId())
                .orElseThrow(ErrorDescription.LESSON_TYPE_NOT_FOUNT::exception);
        for (GroupDto group : addLessonDto.getGroups()) {
            groupRepository.findById(group.getId())
                    .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        }
        LessonEntity lessonEntity = new LessonEntity(addLessonDto);
        lessonRepository.save(lessonEntity);
    }

    /**
     * Изменение значений занятия.
     *
     * @param id идентификатор.
     * @param addLessonDto занятие.
     */
    @Override
    public void updateLesson(long id, AddLessonDto addLessonDto) {

        LessonEntity updated = lessonRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_NOT_FOUNT::exception);
        if (addLessonDto.getClassroom() != null) {
            classroomRepository.findById(addLessonDto.getClassroom().getId())
                    .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
            updated.setClassroom(ClassroomDto.on(addLessonDto.getClassroom()));
        }
        if (addLessonDto.getSubject() != null) {
            subjectRepository.findById(addLessonDto.getSubject().getId())
                    .orElseThrow(ErrorDescription.SUBJECT_NOT_FOUNT::exception);
            updated.setSubject(SubjectDto.on(addLessonDto.getSubject()));
        }
            updated.setStatus(addLessonDto.isStatus());

        if (addLessonDto.getUser() != null) {
            UserEntity userEntity = userRepository.findById(addLessonDto.getUser().getId())
                    .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
            ErrorDescription.ACCESS_IS_DENIED
                    .throwIfFalse(userEntity.getRole() == 2);
            updated.setUser(UserDto.on(addLessonDto.getUser()));
        }
        if (addLessonDto.getSemester()!=null) {
            semesterRepository.findById(addLessonDto.getSemester().getId())
                    .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
            updated.setSemester(SemesterDto.on(addLessonDto.getSemester()));
        }
        if (addLessonDto.getLessonType()!=null) {
            lessonTypeRepository.findById(addLessonDto.getLessonType().getId())
                    .orElseThrow(ErrorDescription.LESSON_TYPE_NOT_FOUNT::exception);
            updated.setLessonType(LessonTypeDto.on(addLessonDto.getLessonType()));
        }
        if (addLessonDto.getGroups()!=null) {
            for (GroupDto group : addLessonDto.getGroups()) {
                groupRepository.findById(group.getId())
                        .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
            }
            updated.setGroups(addLessonDto.getGroups().stream().map(GroupDto::on).collect(Collectors.toList()));
        }
        lessonRepository.save(updated);
    }

    /**
     * Удаление занятия.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteLesson(long id) {
        lessonRepository.deleteById(id);
    }

    /**
     * Возвращение всех существующих занятий.
     *
     * @return список занятий.
     */
    @Override
    public List<LessonDto> findAll() {
        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        return lessonEntities.stream().map(LessonDto::of).collect(Collectors.toList());
    }
}
