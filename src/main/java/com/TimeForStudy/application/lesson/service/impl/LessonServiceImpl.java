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
import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionRepository;
import com.TimeForStudy.application.lessontype.domain.LessonTypeRepository;
import com.TimeForStudy.application.lessontype.model.LessonTypeDto;
import com.TimeForStudy.application.semester.domain.SemesterRepository;
import com.TimeForStudy.application.semester.model.SemesterDto;
import com.TimeForStudy.application.subject.domain.SubjectRepository;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private final ClassroomRepository classroomRepository;

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
     * {@link LessonGridRepository}
     */
    private final LessonGridRepository lessonGridRepository;

    /**
     * {@link UniversityRepository}
     */
    private final UniversityRepository universityRepository;

    private boolean flag;
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
     * Сортировка позиций.
     */
    class SortByPositionLesson implements Comparator<LessonPositionEntity> {
        // Используется для сортировки в порядке возрастания
        // номер

        public int compare(LessonPositionEntity a, LessonPositionEntity b) {
            return a.getNumber() - b.getNumber();
        }
    }

    /**
     * Сортировка групп
     */
    class SortByGroup implements Comparator<GroupEntity> {
        // Используется для сортировки в порядке возрастания
        // номер

        public int compare(GroupEntity a, GroupEntity b) {
            return (int) (a.getId() - b.getId());
        }
    }

    /**
     * Возвращение расписания занятий на день
     *
     * @param addInfoLessonDto информация о расписании.
     * @return список дней.
     */
    @Override
    public List<DaysDto> getLessonInfo(AddInfoLessonDto addInfoLessonDto) {
        //User
        UserEntity userEntity = userRepository.findById(addInfoLessonDto.getUserId())
                .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        //Группы
        List<GroupEntity> groupEntities = userEntity.getGroups();
        // Занятия в этот день
        List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository
                .findAllByPosition(addInfoLessonDto.getWeekNum());
        List<LessonPositionEntity> lessonPositionEntities0 = lessonPositionRepository
                .findAllByPosition(0);
        lessonPositionEntities.addAll(lessonPositionEntities0);
        UniversityEntity universityEntity = universityRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);

        List<DaysDto> daysDtos = new ArrayList<>();

        Collections.sort(lessonPositionEntities, new SortByPositionLesson());

        if (universityEntity.getWorkDays().indexOf('1') != -1) {
            DaysDto daysDto = new DaysDto();
            daysDto.setDayName("Понедельник");
            // Расписание для вывода
            List<InfoLessonDto> infoLessonDtos = new ArrayList<>();

            for (LessonPositionEntity less : lessonPositionEntities) {
                LessonEntity lessonEntity = less.getLesson();
                if (less.getDays() == 1) {
//            if (lessonEntity.getGroups().retainAll(groupEntities)) {
                    InfoLessonDto infoLessonDto = new InfoLessonDto();
                    List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                            findAllByUniversity((lessonEntity.
                                    getSemester().
                                    getUniversity()));
                    for (LessonGridEntity lessGrid : lessonGridEntities) {
                        if (lessGrid.getLessonNumber() == less.getNumber()) {
                            infoLessonDto.setTime(lessGrid.getTime());
                        }
                    }
                    infoLessonDto.setId(less.getId());
                    infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                    infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                    infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                    infoLessonDto.setStatus(lessonEntity.isStatus());
                    infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                    infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                    infoLessonDtos.add(infoLessonDto);
//            }
                }
            }

            daysDto.setInfoLessonDtos(infoLessonDtos);
            daysDtos.add(daysDto);
        }
        if (universityEntity.getWorkDays().indexOf('2') != -1) {
            DaysDto daysDto = new DaysDto();
            daysDto.setDayName("Вторник");
            // Расписание для вывода
            List<InfoLessonDto> infoLessonDtos = new ArrayList<>();

            for (LessonPositionEntity less : lessonPositionEntities) {
                LessonEntity lessonEntity = less.getLesson();
                if (less.getDays() == 2) {
//            if (lessonEntity.getGroups().retainAll(groupEntities)) {
                    InfoLessonDto infoLessonDto = new InfoLessonDto();
                    List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                            findAllByUniversity((lessonEntity.
                                    getSemester().
                                    getUniversity()));
                    for (LessonGridEntity lessGrid : lessonGridEntities) {
                        if (lessGrid.getLessonNumber() == less.getNumber()) {
                            infoLessonDto.setTime(lessGrid.getTime());
                        }
                    }
                    infoLessonDto.setId(less.getId());
                    infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                    infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                    infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                    infoLessonDto.setStatus(lessonEntity.isStatus());
                    infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                    infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                    infoLessonDtos.add(infoLessonDto);
//            }
                }
            }

            daysDto.setInfoLessonDtos(infoLessonDtos);
            daysDtos.add(daysDto);
        }
        if (universityEntity.getWorkDays().indexOf('3') != -1) {
            DaysDto daysDto = new DaysDto();
            daysDto.setDayName("Среда");
            // Расписание для вывода
            List<InfoLessonDto> infoLessonDtos = new ArrayList<>();

            for (LessonPositionEntity less : lessonPositionEntities) {
                LessonEntity lessonEntity = less.getLesson();
                if (less.getDays() == 3) {
//            if (lessonEntity.getGroups().retainAll(groupEntities)) {
                    InfoLessonDto infoLessonDto = new InfoLessonDto();
                    List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                            findAllByUniversity((lessonEntity.
                                    getSemester().
                                    getUniversity()));
                    for (LessonGridEntity lessGrid : lessonGridEntities) {
                        if (lessGrid.getLessonNumber() == less.getNumber()) {
                            infoLessonDto.setTime(lessGrid.getTime());
                        }
                    }
                    infoLessonDto.setId(less.getId());
                    infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                    infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                    infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                    infoLessonDto.setStatus(lessonEntity.isStatus());
                    infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                    infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                    infoLessonDtos.add(infoLessonDto);
//            }
                }
            }

            daysDto.setInfoLessonDtos(infoLessonDtos);
            daysDtos.add(daysDto);
        }
        if (universityEntity.getWorkDays().indexOf('4') != -1) {
            DaysDto daysDto = new DaysDto();
            daysDto.setDayName("Четверг");
            // Расписание для вывода
            List<InfoLessonDto> infoLessonDtos = new ArrayList<>();

            for (LessonPositionEntity less : lessonPositionEntities) {
                LessonEntity lessonEntity = less.getLesson();
                if (less.getDays() == 4) {
//            if (lessonEntity.getGroups().retainAll(groupEntities)) {
                    InfoLessonDto infoLessonDto = new InfoLessonDto();
                    List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                            findAllByUniversity((lessonEntity.
                                    getSemester().
                                    getUniversity()));
                    for (LessonGridEntity lessGrid : lessonGridEntities) {
                        if (lessGrid.getLessonNumber() == less.getNumber()) {
                            infoLessonDto.setTime(lessGrid.getTime());
                        }
                    }
                    infoLessonDto.setId(less.getId());
                    infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                    infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                    infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                    infoLessonDto.setStatus(lessonEntity.isStatus());
                    infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                    infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                    infoLessonDtos.add(infoLessonDto);
//            }
                }
            }

            daysDto.setInfoLessonDtos(infoLessonDtos);
            daysDtos.add(daysDto);
        }
        if (universityEntity.getWorkDays().indexOf('5') != -1) {
            DaysDto daysDto = new DaysDto();
            daysDto.setDayName("Пятница");
            // Расписание для вывода
            List<InfoLessonDto> infoLessonDtos = new ArrayList<>();

            for (LessonPositionEntity less : lessonPositionEntities) {
                LessonEntity lessonEntity = less.getLesson();
                if (less.getDays() == 5) {
//            if (lessonEntity.getGroups().retainAll(groupEntities)) {
                    InfoLessonDto infoLessonDto = new InfoLessonDto();
                    List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                            findAllByUniversity((lessonEntity.
                                    getSemester().
                                    getUniversity()));
                    for (LessonGridEntity lessGrid : lessonGridEntities) {
                        if (lessGrid.getLessonNumber() == less.getNumber()) {
                            infoLessonDto.setTime(lessGrid.getTime());
                        }
                    }
                    infoLessonDto.setId(less.getId());
                    infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                    infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                    infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                    infoLessonDto.setStatus(lessonEntity.isStatus());
                    infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                    infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                    infoLessonDtos.add(infoLessonDto);
//            }
                }
            }

            daysDto.setInfoLessonDtos(infoLessonDtos);
            daysDtos.add(daysDto);
        }
        if (universityEntity.getWorkDays().indexOf('6') != -1) {
            DaysDto daysDto = new DaysDto();
            daysDto.setDayName("Суббота");
            // Расписание для вывода
            List<InfoLessonDto> infoLessonDtos = new ArrayList<>();

            for (LessonPositionEntity less : lessonPositionEntities) {
                LessonEntity lessonEntity = less.getLesson();
                if (less.getDays() == 6) {
//            if (lessonEntity.getGroups().retainAll(groupEntities)) {
                    InfoLessonDto infoLessonDto = new InfoLessonDto();
                    List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                            findAllByUniversity((lessonEntity.
                                    getSemester().
                                    getUniversity()));
                    for (LessonGridEntity lessGrid : lessonGridEntities) {
                        if (lessGrid.getLessonNumber() == less.getNumber()) {
                            infoLessonDto.setTime(lessGrid.getTime());
                        }
                    }
                    infoLessonDto.setId(less.getId());
                    infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                    infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                    infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                    infoLessonDto.setStatus(lessonEntity.isStatus());
                    infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                    infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                    infoLessonDtos.add(infoLessonDto);
//            }
                }
            }

            daysDto.setInfoLessonDtos(infoLessonDtos);
            daysDtos.add(daysDto);
        }
        if (universityEntity.getWorkDays().indexOf('7') != -1) {
            DaysDto daysDto = new DaysDto();
            daysDto.setDayName("Воскресенье");
            // Расписание для вывода
            List<InfoLessonDto> infoLessonDtos = new ArrayList<>();

            for (LessonPositionEntity less : lessonPositionEntities) {
                LessonEntity lessonEntity = less.getLesson();
                if (less.getDays() == 7) {
//            if (lessonEntity.getGroups().retainAll(groupEntities)) {
                    InfoLessonDto infoLessonDto = new InfoLessonDto();
                    List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                            findAllByUniversity((lessonEntity.
                                    getSemester().
                                    getUniversity()));
                    for (LessonGridEntity lessGrid : lessonGridEntities) {
                        if (lessGrid.getLessonNumber() == less.getNumber()) {
                            infoLessonDto.setTime(lessGrid.getTime());
                        }
                    }
                    infoLessonDto.setId(less.getId());
                    infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                    infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                    infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                    infoLessonDto.setStatus(lessonEntity.isStatus());
                    infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                    infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                    infoLessonDtos.add(infoLessonDto);
//            }
                }
            }

            daysDto.setInfoLessonDtos(infoLessonDtos);
            daysDtos.add(daysDto);
        }

        return daysDtos;
    }

    /**
     * Фильтрация пар.
     */

    /**
     * Сохранение занятия.
     *
     * @param newLessonDto занятие.
     */
    @Override
    public BoolLessonDto saveLesson(NewLessonDto newLessonDto) {

        BoolLessonDto boolLessonDto = new BoolLessonDto();
        List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository
                .findAllByPositionAndNumberAndAndDays(
                        newLessonDto.getPosition()/100,
                        newLessonDto.getPosition()/10,
                        newLessonDto.getPosition()%10
                );
        for (LessonPositionEntity less : lessonPositionEntities) {

            // Проверка групп.
//            if (less.getLesson().getGroups().retainAll(newLessonDto.getGroups())) {
//            }
            // Проверка профессора.
            if (less.getLesson().getUser().getName().equals(newLessonDto.getProfessor())) {
                boolLessonDto.setProfessor(false);
            }
            // Проверка кабинета.
            if (less.getLesson().getClassroom().getNumber()==newLessonDto.getClassroom()) {
                boolLessonDto.setClassroom(false);
            }
            // Проверка предмета.
            if (subjectRepository.findAllByName(newLessonDto.getSubject())==null) {
                boolLessonDto.setSubject(false);
            }
        }
        if ((boolLessonDto.isClassroom())&&(boolLessonDto.isProfessor())&&(boolLessonDto.isSubject())) {
            flag = true;
            for (boolean group : boolLessonDto.getGroups()) {
                if (!group) {
                    flag = false;
                }
            }
            if (flag) {
                LessonEntity lessonEntity = new LessonEntity();
                //
                // Сохранение будет здесь
                //
                lessonRepository.save(lessonEntity);
            }
        }
        return boolLessonDto;
    }

    /**
     * Изменение значений занятия.
     *
     * @param id           идентификатор.
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
        if (addLessonDto.getSemester() != null) {
            semesterRepository.findById(addLessonDto.getSemester().getId())
                    .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
            updated.setSemester(SemesterDto.on(addLessonDto.getSemester()));
        }
        if (addLessonDto.getLessonType() != null) {
            lessonTypeRepository.findById(addLessonDto.getLessonType().getId())
                    .orElseThrow(ErrorDescription.LESSON_TYPE_NOT_FOUNT::exception);
            updated.setLessonType(LessonTypeDto.on(addLessonDto.getLessonType()));
        }
        if (addLessonDto.getGroups() != null) {
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
