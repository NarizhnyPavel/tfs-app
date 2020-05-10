package com.TimeForStudy.application.lesson.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.classroom.domain.ClassroomRepository;
import com.TimeForStudy.application.classroom.model.ClassroomDto;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.group.model.GroupsDto;
import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lesson.domain.LessonRepository;
import com.TimeForStudy.application.lesson.model.*;
import com.TimeForStudy.application.lesson.service.LessonService;
import com.TimeForStudy.application.lessongrid.domain.LessonGridEntity;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionRepository;
import com.TimeForStudy.application.lessontype.domain.LessonTypeEntity;
import com.TimeForStudy.application.lessontype.domain.LessonTypeRepository;
import com.TimeForStudy.application.lessontype.model.LessonTypeDto;
import com.TimeForStudy.application.semester.domain.SemesterEntity;
import com.TimeForStudy.application.semester.domain.SemesterRepository;
import com.TimeForStudy.application.semester.model.SemesterDto;
import com.TimeForStudy.application.subject.domain.SubjectEntity;
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

import javax.sound.midi.Soundbank;
import javax.swing.text.Position;
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
     * Возвращение расписания занятий  для студента.
     *
     * @param addInfoLessonDto информация о расписании.
     * @return список дней.
     */
    @Override
    public List<DaysDto> getLessonInfo(AddInfoLessonDto addInfoLessonDto) {

        //User
        UserEntity userEntity = userRepository.findById(addInfoLessonDto.getUserId())
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        //Группы
        List<GroupEntity> groupEntities = userEntity.getGroups();

        UniversityEntity universityEntity = universityRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);

        List<DaysDto> daysDtos = new ArrayList<>();

        if (universityEntity.getWorkDays().indexOf('1') != -1) {
            daysDtos.add(formDaysDto(groupEntities, "Понедельник", addInfoLessonDto.getWeekNum(), 1));
        }
        if (universityEntity.getWorkDays().indexOf('2') != -1) {
            daysDtos.add(formDaysDto(groupEntities, "Вторник", addInfoLessonDto.getWeekNum(), 2));
        }
        if (universityEntity.getWorkDays().indexOf('3') != -1) {
            daysDtos.add(formDaysDto(groupEntities, "Среда", addInfoLessonDto.getWeekNum(), 3));
        }
        if (universityEntity.getWorkDays().indexOf('4') != -1) {
            daysDtos.add(formDaysDto(groupEntities, "Четверг", addInfoLessonDto.getWeekNum(), 4));
        }
        if (universityEntity.getWorkDays().indexOf('5') != -1) {
            daysDtos.add(formDaysDto(groupEntities, "Пятница", addInfoLessonDto.getWeekNum(), 5));
        }
        if (universityEntity.getWorkDays().indexOf('6') != -1) {
            daysDtos.add(formDaysDto(groupEntities, "Суббота", addInfoLessonDto.getWeekNum(), 6));
        }
        if (universityEntity.getWorkDays().indexOf('7') != -1) {
            daysDtos.add(formDaysDto(groupEntities, "Воскресенье", addInfoLessonDto.getWeekNum(), 7));
        }
        return daysDtos;
    }

    /**
     * Формирование расписания на день
     */
    public DaysDto formDaysDto(List<GroupEntity> groupEntities, String nameDay, Integer weekNum, Integer numberDay) {

        DaysDto daysDto = new DaysDto();
        daysDto.setDayName(nameDay);

        List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository
                .findAllByPositionAndDays(weekNum, numberDay);
        List<LessonPositionEntity> lessonPositionEntities0 = lessonPositionRepository
                .findAllByPositionAndDays(0, numberDay);
        lessonPositionEntities.addAll(lessonPositionEntities0);
        Collections.sort(lessonPositionEntities, new SortByPositionLesson());

        List<InfoLessonDto> infoLessonDtos = new ArrayList<>();

        for (LessonPositionEntity less : lessonPositionEntities) {
            LessonEntity lessonEntity = less.getLesson();
            List<GroupEntity> groups = lessonEntity.getGroups();

            boolean flag = false;
            for (GroupEntity group : groupEntities) {
                if (groups.contains(group)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {

                InfoLessonDto infoLessonDto = new InfoLessonDto();
                List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                        findAllByUniversity(lessonEntity.
                                getSemester().
                                getUniversity());
                for (LessonGridEntity lessGrid : lessonGridEntities) {
                    if (lessGrid.getLessonNumber() == less.getNumber()) {
                        infoLessonDto.setTime(lessGrid.getTime());
                        break;
                    }
                }
                infoLessonDto.setId(less.getId());
                infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                infoLessonDto.setStatus(lessonEntity.isStatus());
                infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                String groups1 = "";
                for (GroupEntity group : lessonEntity.getGroups()) {
                    groups1+=group.getNumber()+" ";
                }
                infoLessonDto.setGroup(groups1);
                infoLessonDtos.add(infoLessonDto);
            }

        }

        daysDto.setInfoLessonDtos(infoLessonDtos);

        return daysDto;
    }

    /**
     * Возвращение расписания на поиск.
     *
     * @param lessonByDto информация о расписании.
     * @return список расписания по дням.
     */
    @Override
    public List<DaysDto> getLessonBy(LessonByDto lessonByDto) {
        List<DaysDto> daysDtos = new ArrayList<>();

        UniversityEntity universityEntity = universityRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);

        if (lessonByDto.getType() == 1) {
            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                daysDtos.add(formLessonByProfessor(lessonByDto.getId(), "Понедельник", lessonByDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                daysDtos.add(formLessonByProfessor(lessonByDto.getId(), "Вторник", lessonByDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                daysDtos.add(formLessonByProfessor(lessonByDto.getId(), "Среда", lessonByDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                daysDtos.add(formLessonByProfessor(lessonByDto.getId(), "Четверг", lessonByDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                daysDtos.add(formLessonByProfessor(lessonByDto.getId(), "Пятница", lessonByDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                daysDtos.add(formLessonByProfessor(lessonByDto.getId(), "Суббота", lessonByDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                daysDtos.add(formLessonByProfessor(lessonByDto.getId(), "Воскресенье", lessonByDto.getWeekNum(), 7));
            }
        }
        if (lessonByDto.getType() == 0) {
            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                daysDtos.add(formLessonByGroup(lessonByDto.getId(), "Понедельник", lessonByDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                daysDtos.add(formLessonByGroup(lessonByDto.getId(), "Вторник", lessonByDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                daysDtos.add(formLessonByGroup(lessonByDto.getId(), "Среда", lessonByDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                daysDtos.add(formLessonByGroup(lessonByDto.getId(), "Четверг", lessonByDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                daysDtos.add(formLessonByGroup(lessonByDto.getId(), "Пятница", lessonByDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                daysDtos.add(formLessonByGroup(lessonByDto.getId(), "Суббота", lessonByDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                daysDtos.add(formLessonByGroup(lessonByDto.getId(), "Воскресенье", lessonByDto.getWeekNum(), 7));
            }
        }
        if (lessonByDto.getType() == 2) {
            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                daysDtos.add(formLessonByClassroom(lessonByDto.getId(), "Понедельник", lessonByDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                daysDtos.add(formLessonByClassroom(lessonByDto.getId(), "Вторник", lessonByDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                daysDtos.add(formLessonByClassroom(lessonByDto.getId(), "Среда", lessonByDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                daysDtos.add(formLessonByClassroom(lessonByDto.getId(), "Четверг", lessonByDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                daysDtos.add(formLessonByClassroom(lessonByDto.getId(), "Пятница", lessonByDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                daysDtos.add(formLessonByClassroom(lessonByDto.getId(), "Суббота", lessonByDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                daysDtos.add(formLessonByClassroom(lessonByDto.getId(), "Воскресенье", lessonByDto.getWeekNum(), 7));
            }
        }

        return daysDtos;
    }

    /**
     * Формирование расписания для преподавателя.
     */
    public DaysDto formLessonByProfessor(Long id, String nameDay, Integer weekNum, Integer numberDay) {

        DaysDto daysDto = new DaysDto();
        daysDto.setDayName(nameDay);

        List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository
                .findAllByPositionAndDays(weekNum, numberDay);
        List<LessonPositionEntity> lessonPositionEntities0 = lessonPositionRepository
                .findAllByPositionAndDays(0, numberDay);
        lessonPositionEntities.addAll(lessonPositionEntities0);
        Collections.sort(lessonPositionEntities, new SortByPositionLesson());

        List<InfoLessonDto> infoLessonDtos = new ArrayList<>();


        for (LessonPositionEntity less : lessonPositionEntities) {
            LessonEntity lessonEntity = less.getLesson();

            if (lessonEntity.getUser().getId() == id) {

                InfoLessonDto infoLessonDto = new InfoLessonDto();
                List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                        findAllByUniversity(lessonEntity.
                                getSemester().
                                getUniversity());
                for (LessonGridEntity lessGrid : lessonGridEntities) {
                    if (lessGrid.getLessonNumber() == less.getNumber()) {
                        infoLessonDto.setTime(lessGrid.getTime());
                        break;
                    }
                }
                infoLessonDto.setId(less.getId());
                infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                infoLessonDto.setStatus(lessonEntity.isStatus());
                infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                String groups = "";
                for (GroupEntity group : lessonEntity.getGroups()) {
                    groups+=group.getNumber()+" ";
                }
                infoLessonDto.setGroup(groups);
                infoLessonDtos.add(infoLessonDto);
            }

        }
        daysDto.setInfoLessonDtos(infoLessonDtos);

        return daysDto;
    }

    /**
     * Формирование расписания для группы.
     */
    public DaysDto formLessonByGroup(Long id, String nameDay, Integer weekNum, Integer numberDay) {

        DaysDto daysDto = new DaysDto();
        daysDto.setDayName(nameDay);

        List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository
                .findAllByPositionAndDays(weekNum, numberDay);
        List<LessonPositionEntity> lessonPositionEntities0 = lessonPositionRepository
                .findAllByPositionAndDays(0, numberDay);
        lessonPositionEntities.addAll(lessonPositionEntities0);
        Collections.sort(lessonPositionEntities, new SortByPositionLesson());

        List<InfoLessonDto> infoLessonDtos = new ArrayList<>();


        for (LessonPositionEntity less : lessonPositionEntities) {
            LessonEntity lessonEntity = less.getLesson();

            GroupEntity groupEntity = groupRepository.findById(id)
                    .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);

            if (lessonEntity.getGroups().contains(groupEntity)) {

                InfoLessonDto infoLessonDto = new InfoLessonDto();
                List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                        findAllByUniversity(lessonEntity.
                                getSemester().
                                getUniversity());
                for (LessonGridEntity lessGrid : lessonGridEntities) {
                    if (lessGrid.getLessonNumber() == less.getNumber()) {
                        infoLessonDto.setTime(lessGrid.getTime());
                        break;
                    }
                }
                infoLessonDto.setId(less.getId());
                infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                infoLessonDto.setStatus(lessonEntity.isStatus());
                infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                String groups = "";
                for (GroupEntity group : lessonEntity.getGroups()) {
                    groups+=group.getNumber()+" ";
                }
                infoLessonDto.setGroup(groups);
                infoLessonDtos.add(infoLessonDto);
            }

        }

        daysDto.setInfoLessonDtos(infoLessonDtos);

        return daysDto;
    }

    /**
     * Формирование расписания для кабинеты.
     */
    public DaysDto formLessonByClassroom(Long id, String nameDay, Integer weekNum, Integer numberDay) {

        DaysDto daysDto = new DaysDto();
        daysDto.setDayName(nameDay);

        List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository
                .findAllByPositionAndDays(weekNum, numberDay);
        List<LessonPositionEntity> lessonPositionEntities0 = lessonPositionRepository
                .findAllByPositionAndDays(0, numberDay);
        lessonPositionEntities.addAll(lessonPositionEntities0);
        Collections.sort(lessonPositionEntities, new SortByPositionLesson());

        List<InfoLessonDto> infoLessonDtos = new ArrayList<>();


        for (LessonPositionEntity less : lessonPositionEntities) {
            LessonEntity lessonEntity = less.getLesson();

            if (lessonEntity.getClassroom().getId() == id) {

                InfoLessonDto infoLessonDto = new InfoLessonDto();
                List<LessonGridEntity> lessonGridEntities = lessonGridRepository.
                        findAllByUniversity(lessonEntity.
                                getSemester().
                                getUniversity());
                for (LessonGridEntity lessGrid : lessonGridEntities) {
                    if (lessGrid.getLessonNumber() == less.getNumber()) {
                        infoLessonDto.setTime(lessGrid.getTime());
                        break;
                    }
                }
                infoLessonDto.setId(less.getId());
                infoLessonDto.setClassroom(lessonEntity.getClassroom().getNumber());
                infoLessonDto.setSubject(lessonEntity.getSubject().getName());
                infoLessonDto.setArc(lessonEntity.getSubject().getArc());
                infoLessonDto.setStatus(lessonEntity.isStatus());
                infoLessonDto.setProfessor(lessonEntity.getUser().getName());
                infoLessonDto.setLessonType(lessonEntity.getLessonType().getName());
                String groups = "";
                for (GroupEntity group : lessonEntity.getGroups()) {
                    groups+=group.getNumber()+" ";
                }
                infoLessonDto.setGroup(groups);
                infoLessonDtos.add(infoLessonDto);
            }

        }

        daysDto.setInfoLessonDtos(infoLessonDtos);

        return daysDto;
    }

    /**
     * Валидация поиска.
     *
     * @param validateSearch строка валидации и тип.
     * @return коллекци валидации поиска.
     */
    @Override
    public List<SearchDto> getSearch(ValidateSearch validateSearch) {
        List<SearchDto> searchDtos = new ArrayList<>();

        List<UserEntity> userEntities = userRepository.findAllByRole((byte) 2);

        if (validateSearch.getType().isClassroom()) {
            List<ClassroomEntity> classroomEntities = classroomRepository.findAll();

            for (ClassroomEntity classroom : classroomEntities) {
                if (String.valueOf(classroom.getNumber()).contains(validateSearch.getRequest())) {
                    searchDtos.add(new SearchDto(classroom.getId(), String.valueOf(classroom.getNumber()), 2));
                }
            }
        }

        if (validateSearch.getType().isGroup()) {
            List<GroupEntity> groupEntities = groupRepository.findAll();

            for (GroupEntity group : groupEntities) {
                if (group.getNumber().contains(validateSearch.getRequest())) {
                    searchDtos.add(new SearchDto(group.getId(), group.getNumber(), 0));
                }
            }
        }
        if (validateSearch.getType().isProfessor()) {
            for (UserEntity user : userEntities) {
                if (user.getName().contains(validateSearch.getRequest())) {
                    searchDtos.add(new SearchDto(user.getId(), user.getName(), 1));
                }
            }
        }
        return searchDtos;
    }

    /**
     * Сохранение занятия.
     *
     * @param newLessonDto занятие.
     */
    @Override
    public List<BoolLessonDto> saveLesson(NewLessonDto newLessonDto) {

        boolean mainFlag = false;
        List<BoolLessonDto> boolLessonDtos = new ArrayList<>();
        ClassroomEntity classroom = classroomRepository.findById(newLessonDto.getClassroom())
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        UserEntity userEntity = userRepository.findById(newLessonDto.getProfessor())
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        SemesterEntity semesterEntity = semesterRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        SubjectEntity subjectEntity = subjectRepository.findById(newLessonDto.getSubject())
                .orElseThrow(ErrorDescription.SUBJECT_NOT_FOUNT::exception);
        List<GroupEntity> groupEntities = new ArrayList<>();
        for (AddLessonGroup group : newLessonDto.getGroups()) {
            GroupEntity groupEntity = groupRepository.findById(group.getId())
                    .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
            groupEntities.add(groupEntity);
        }
        LessonTypeEntity lessonTypeEntity = lessonTypeRepository.findById(newLessonDto.getLessonType())
                .orElseThrow(ErrorDescription.LESSON_TYPE_NOT_FOUNT::exception);

        for (PositionDto positionDto : newLessonDto.getPosition()) {
            BoolLessonDto boolLessonDto = new BoolLessonDto();
            List<AddLessonGroup> addLessonGroup = newLessonDto.getGroups();
            List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository
                    .findAllByPositionAndDaysAndNumber(
                            Integer.parseInt(positionDto.getNum().substring(0, 1)),
                            Integer.parseInt(positionDto.getNum().substring(2, 3)),
                            Integer.parseInt(positionDto.getNum().substring(1, 2))
                    );
            if (Integer.parseInt(positionDto.getNum().substring(0, 1)) != 0) {
                List<LessonPositionEntity> lessonPositionEntities0 = lessonPositionRepository
                        .findAllByPositionAndDaysAndNumber(
                                0,
                                Integer.parseInt(positionDto.getNum().substring(2, 3)),
                                Integer.parseInt(positionDto.getNum().substring(1, 2))
                        );
                lessonPositionEntities.addAll(lessonPositionEntities0);
            }
            boolean flagClassroom = true;
            boolean flagProfessor = true;
            for (LessonPositionEntity position : lessonPositionEntities) {
                //проверка кабинета
                if (flagClassroom && (position.getLesson().getClassroom() == classroom)) {
                    boolLessonDto.setClassroom(0);
                    mainFlag = true;
                    flagClassroom = false;
                }
                //проверка преподавателя
                if (flagProfessor && (position.getLesson().getUser() == userEntity)) {
                    boolLessonDto.setProfessor(0);
                    mainFlag = true;
                    flagProfessor = false;
                }
                //проверка групп
                for (AddLessonGroup group : addLessonGroup) {
                    if (position.getLesson().getGroups().contains(group)) {
                        group.setNumber(0);
                        mainFlag = true;
                    }
                }

            }
//            if (mainFlag) {
//                LessonEntity lessonEntity = new LessonEntity();
//                lessonEntity.setGroups(groupEntities);
//                lessonEntity.setLessonType(lessonTypeEntity);
//                lessonEntity.setStatus(true);
//                lessonEntity.setSemester(semesterEntity);
//                lessonEntity.setUser(userEntity);
//                lessonEntity.setClassroom(classroom);
//                lessonEntity.setSubject(subjectEntity);
//                lessonRepository.save(lessonEntity);
//            }
            boolLessonDto.setPosition(positionDto.getNum());
            boolLessonDto.setGroups(addLessonGroup);
            boolLessonDtos.add(boolLessonDto);
        }

        return boolLessonDtos;
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
