package com.TimeForStudy.application.lesson.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.classroom.domain.ClassroomRepository;
import com.TimeForStudy.application.date.model.DateDto;
import com.TimeForStudy.application.date.service.DateService;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lesson.domain.LessonRepository;
import com.TimeForStudy.application.lesson.model.*;
import com.TimeForStudy.application.lesson.service.LessonService;
import com.TimeForStudy.application.lessongrid.domain.LessonGridRepository;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionEntity;
import com.TimeForStudy.application.lessonposition.domain.LessonPositionRepository;
import com.TimeForStudy.application.lessontype.domain.LessonTypeEntity;
import com.TimeForStudy.application.lessontype.domain.LessonTypeRepository;
import com.TimeForStudy.application.notification.domain.NotificationEntity;
import com.TimeForStudy.application.notification.domain.NotificationRepository;
import com.TimeForStudy.application.notification.service.NotificationService;
import com.TimeForStudy.application.positioncancel.domain.PositionCancelEntity;
import com.TimeForStudy.application.positioncancel.domain.PositionCancelRepository;
import com.TimeForStudy.application.semester.domain.SemesterEntity;
import com.TimeForStudy.application.semester.domain.SemesterRepository;
import com.TimeForStudy.application.subject.domain.SubjectEntity;
import com.TimeForStudy.application.subject.domain.SubjectRepository;
import com.TimeForStudy.application.university.domain.UniversityEntity;
import com.TimeForStudy.application.university.domain.UniversityRepository;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
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

    /**
     * {@link UniversityRepository}
     */
    private final PositionCancelRepository positionCancelRepository;

    /**
     * {@link DateService}
     */
    private final DateService dateService;

    /**
     * {@link NotificationRepository}
     */
    private final NotificationRepository notificationRepository;

    /**
     * {@link NotificationService}
     */
    private final NotificationService notificationService;

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
     * Возвращает данные для переноса лекции.
     *
     * @param id идентификатор.
     * @return занятие
     */
    @Override
    public LessonEditInfo getLessonEdit(long id) {

        LessonPositionEntity lessonPositionEntity = lessonPositionRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_POSITION_NOT_FOUNT::exception);
        LessonEditInfo lessonEditInfo = new LessonEditInfo();
        lessonEditInfo.setClassroom(Integer.toString(lessonPositionEntity.getClassroom().getNumber()));
        lessonEditInfo.setClassroomId(lessonPositionEntity.getClassroom().getId());
        lessonEditInfo.setLessonPosition(
                Integer.toString(lessonPositionEntity.getWeek()) + "" +
                        Integer.toString(lessonPositionEntity.getDays()) + "" +
                        Integer.toString(lessonPositionEntity.getNumber())

        );
        lessonEditInfo.setLessonType(lessonPositionEntity.getLesson().getLessonType().getName());
        lessonEditInfo.setProfessorId(lessonPositionEntity.getLesson().getUser().getId());
        lessonEditInfo.setSubjectId(lessonPositionEntity.getLesson().getSubject().getId());
        lessonEditInfo.setGroups(lessonPositionEntity.getLesson().getGroups().stream().map(AddLessonGroup::of).collect(Collectors.toList()));

        return lessonEditInfo;
    }

    /**
     * Возвращение расписания занятий для студента.
     *
     * @param lessonStopDto информация о лекции.
     * @return статус.
     */
    @Override
    public String inLessonStop(LessonStopDto lessonStopDto) {

        LessonPositionEntity lessonPosition = lessonPositionRepository.findById(lessonStopDto.getId())
                .orElseThrow(ErrorDescription.LESSON_POSITION_NOT_FOUNT::exception);
        DateDto dateDto = dateService
                .getWeekNow(lessonPosition.getLesson().getSemester().getId());
        LocalDate localDate = dateService.getDayRequest(
                lessonPosition.getLesson().getSemester().getUniversity().getWeeks(),
                dateDto.getNumberWeek(),
                dateDto.getNumberDay(),
                lessonStopDto.getWeeks(),
                lessonPosition.getDays()
                );
        localDate = localDate.plusDays(1);
        PositionCancelEntity positionCancelEntity = new PositionCancelEntity(localDate, lessonStopDto.getWeeks(), lessonPosition);
        positionCancelRepository.save(positionCancelEntity);
        //формирование уведомления
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setType(false);
        notificationEntity.setLessonPosition(lessonStopDto.getWeeks() +
                "" + lessonPosition.getDays() +
                "" + lessonPosition.getNumber()
        );
        notificationEntity.setSender(lessonPosition.getLesson().getUser());
        notificationEntity.setLessons(lessonPosition);
        notificationEntity.setDate(localDate);
        notificationRepository.save(notificationEntity);
        return "success";
    }

    /**
     * Возвращение расписания занятий  для студента.
     *
     * @param getInfoLessonDto информация о расписании.
     * @return список дней.
     */
    @Override
    public List<DayDto> getLessonInfo(GetInfoLessonDto getInfoLessonDto) {

        //User
        UserEntity userEntity = userRepository.findById(getInfoLessonDto.getUserId())
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        if (userEntity.getRole()==2) {
            return getLessonBy( new LessonByDto(userEntity.getId(), getInfoLessonDto.getWeekNum(),1));
        } else {
            //Группы
            List<GroupEntity> groupEntities = userEntity.getGroups();
            UniversityEntity universityEntity = universityRepository.findById((long) 1)
                    .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
            List<DayDto> dayDtos = new ArrayList<>();

            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                dayDtos.add(formDayDto(userEntity, "Понедельник", getInfoLessonDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                dayDtos.add(formDayDto(userEntity, "Вторник", getInfoLessonDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                dayDtos.add(formDayDto(userEntity, "Среда", getInfoLessonDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                dayDtos.add(formDayDto(userEntity, "Четверг", getInfoLessonDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                dayDtos.add(formDayDto(userEntity, "Пятница", getInfoLessonDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                dayDtos.add(formDayDto(userEntity, "Суббота", getInfoLessonDto.getWeekNum(), 6));
//                daysDtos.add(formDaysDto(groupEntities, "Суббота", addInfoLessonDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                dayDtos.add(formDayDto(userEntity, "Воскресенье", getInfoLessonDto.getWeekNum(), 7));
//                daysDtos.add(formDaysDto(groupEntities, "Воскресенье", addInfoLessonDto.getWeekNum(), 7));
            }
            return dayDtos;
        }
    }

    /**
     * Возвращение расписания на поиск.
     *
     * @param lessonByDto информация о расписании.
     * @return список расписания по дням.
     */
    @Override
    public List<DayDto> getLessonBy(LessonByDto lessonByDto) {
        List<DayDto> dayDtos = new ArrayList<>();

        UniversityEntity universityEntity = universityRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);

        if (lessonByDto.getType() == 1) {
            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "Понедельник", lessonByDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "Вторник", lessonByDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "Среда", lessonByDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "Четверг", lessonByDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "Пятница", lessonByDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "Суббота", lessonByDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "Воскресенье", lessonByDto.getWeekNum(), 7));
            }
        }
        if (lessonByDto.getType() == 0) {
            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "Понедельник", lessonByDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "Вторник", lessonByDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "Среда", lessonByDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "Четверг", lessonByDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "Пятница", lessonByDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "Суббота", lessonByDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "Воскресенье", lessonByDto.getWeekNum(), 7));
            }
        }
        if (lessonByDto.getType() == 2) {
            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "Понедельник", lessonByDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "Вторник", lessonByDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "Среда", lessonByDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "Четверг", lessonByDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "Пятница", lessonByDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "Суббота", lessonByDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "Воскресенье", lessonByDto.getWeekNum(), 7));
            }
        }

        return dayDtos;
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
                if (group.getNumber().toLowerCase().contains(validateSearch.getRequest().toLowerCase())) {
                    searchDtos.add(new SearchDto(group.getId(), group.getNumber(), 0));
                }
            }
        }
        if (validateSearch.getType().isProfessor()) {
            for (UserEntity user : userEntities) {
                if (user.getName().toLowerCase().contains(validateSearch.getRequest().toLowerCase())) {
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
    public List<BoolLessonDto> checkLesson(NewLessonDto newLessonDto) {

        List<BoolLessonDto> boolLessonDtos = new ArrayList<>();
        ClassroomEntity classroom = classroomRepository.findById(newLessonDto.getClassroom())
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        UserEntity userEntity = userRepository.findById(newLessonDto.getProfessor())
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        List<GroupEntity> groupEntities = new ArrayList<>();
        for (AddLessonGroup group : newLessonDto.getGroups()) {
            GroupEntity groupEntity = groupRepository.findById(group.getId())
                    .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
            groupEntities.add(groupEntity);
        }
        for (PositionDto positionDto : newLessonDto.getPosition()) {

            BoolLessonDto boolLessonDto = new BoolLessonDto();

            List<AddLessonGroup> lessonGroups = new ArrayList<>();
            for (AddLessonGroup group: newLessonDto.getGroups()) {
                AddLessonGroup addLessonGroup = new AddLessonGroup();
                addLessonGroup.setNumber(1);
                addLessonGroup.setId(group.getId());
                addLessonGroup.setLabel(group.getLabel());
                lessonGroups.add(addLessonGroup);
            }
            boolLessonDto.setGroups(lessonGroups);
            List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository
                    .findAllByWeekAndDaysAndNumber(
                            Integer.parseInt(positionDto.getNum().substring(0, 1)),
                            Integer.parseInt(positionDto.getNum().substring(1, 2)),
                            Integer.parseInt(positionDto.getNum().substring(2, 3))
                    );
            if (Integer.parseInt(positionDto.getNum().substring(0, 1)) != 0) {
                List<LessonPositionEntity> lessonPositionEntities0 = lessonPositionRepository
                        .findAllByWeekAndDaysAndNumber(
                                0,
                                Integer.parseInt(positionDto.getNum().substring(1, 2)),
                                Integer.parseInt(positionDto.getNum().substring(2, 3))
                        );
                lessonPositionEntities.addAll(lessonPositionEntities0);
            } else {
                List<LessonPositionEntity> lessonPositionEntities1 = lessonPositionRepository
                        .findAllByWeekAndDaysAndNumber(
                                1,
                                Integer.parseInt(positionDto.getNum().substring(1, 2)),
                                Integer.parseInt(positionDto.getNum().substring(2, 3))
                        );
                lessonPositionEntities.addAll(lessonPositionEntities1);
                List<LessonPositionEntity> lessonPositionEntities2 = lessonPositionRepository
                        .findAllByWeekAndDaysAndNumber(
                                2,
                                Integer.parseInt(positionDto.getNum().substring(1, 2)),
                                Integer.parseInt(positionDto.getNum().substring(2, 3))
                        );
                lessonPositionEntities.addAll(lessonPositionEntities2);
            }
            for (LessonPositionEntity position : lessonPositionEntities) {
                //проверка кабинета
                if (position.getClassroom() == classroom) {
                    boolLessonDto.setClassroom(0);
                }
                //проверка преподавателя
                if (position.getLesson().getUser() == userEntity) {
                    boolLessonDto.setProfessor(0);
                }
                //проверка групп
                int i = 0;
                for (AddLessonGroup group1 : boolLessonDto.getGroups()) {
                    if (position.getLesson().getGroups().contains(groupEntities.get(i))) {
                        group1.setNumber(0);
                    }
                    i++;
                }
            }

            boolLessonDto.setPosition(positionDto.getNum());

            boolLessonDtos.add(boolLessonDto);
        }
        return boolLessonDtos;
    }

    /**
     * Сохранение занятия.
     *
     * @param newLessonDto занятие.
     */
    @Override
    public String addLesson(NewLessonDto newLessonDto) {

        ClassroomEntity classroom = classroomRepository.findById(newLessonDto.getClassroom())
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        UserEntity userEntity = userRepository.findById(newLessonDto.getProfessor())
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        SemesterEntity semesterEntity = semesterRepository.findById((long) 1) //переделать под семестры
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
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setGroups(groupEntities);
        lessonEntity.setLessonType(lessonTypeEntity);
        lessonEntity.setStatus(true);
        lessonEntity.setSemester(semesterEntity);
        lessonEntity.setUser(userEntity);
        lessonEntity.setClassroom(classroom);
        lessonEntity.setSubject(subjectEntity);
        lessonRepository.save(lessonEntity);
        for (PositionDto positionDto : newLessonDto.getPosition()) {
            LessonPositionEntity lessonPositionEntity = new LessonPositionEntity();
            lessonPositionEntity.setClassroom(classroom);
            lessonPositionEntity.setDays(Integer.parseInt(positionDto.getNum().substring(1,2)));
            lessonPositionEntity.setWeek(Integer.parseInt(positionDto.getNum().substring(0,1)));
            lessonPositionEntity.setNumber(Integer.parseInt(positionDto.getNum().substring(2,3)));
            lessonPositionEntity.setLesson(lessonEntity);
            lessonPositionRepository.save(lessonPositionEntity);
        }

        return "success";
    }

    /**
     * Изменение значений занятия.
     *
     * @param updatePosition новая информация.
     */
    @Override
    public String updateLesson(UpdatePosition updatePosition) {

        LessonPositionEntity lessonPositionEntity = lessonPositionRepository
                .findById(updatePosition.getOldPositionId())
                .orElseThrow(ErrorDescription.LESSON_POSITION_NOT_FOUNT::exception);
        ClassroomEntity classroomEntity = classroomRepository.findById(updatePosition.getNewClassroomId())
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        lessonPositionEntity.setClassroom(classroomEntity);
        lessonPositionEntity.setWeek(Integer.parseInt(updatePosition.getNewPositionNum().substring(0,1)));
        lessonPositionEntity.setDays(Integer.parseInt(updatePosition.getNewPositionNum().substring(1,2)));
        lessonPositionEntity.setNumber(Integer.parseInt(updatePosition.getNewPositionNum().substring(2,3)));
        lessonPositionRepository.save(lessonPositionEntity);

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setType(true);
        notificationEntity.setLessonPosition(updatePosition.getNewPositionNum());
        notificationEntity.setSender(lessonPositionEntity.getLesson().getUser());
        notificationEntity.setLessons(lessonPositionEntity);

        // Текущий семестр
        SemesterEntity semesterEntity = semesterRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        DateDto dateDto = dateService.getWeekNow(semesterEntity.getId());
        // рассчёт даты выбранного дня
        LocalDate localDateRequest = dateService.getDayRequest(
                semesterEntity.getUniversity().getWeeks(),
                dateDto.getNumberWeek(),
                dateDto.getNumberDay(),
                Integer.parseInt(updatePosition.getNewPositionNum().substring(0,1)),
                Integer.parseInt(updatePosition.getNewPositionNum().substring(1,2))
        );
        localDateRequest = localDateRequest.plusDays(1);
        notificationEntity.setDate(localDateRequest);
        notificationRepository.save(notificationEntity);
        return "success";
    }

    /**
     * Удаление занятия.
     *
     * @param id идентификатор.
     */
    @Override
    public String deleteLesson(long id) {
        LessonPositionEntity lessonPositionEntity = lessonPositionRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_POSITION_NOT_FOUNT::exception);
        if (lessonPositionEntity.getLesson().getLessonPositions().size()==1) {
            lessonPositionRepository.deleteById(id);
            lessonRepository.deleteById(lessonPositionEntity.getLesson().getId());
        } else {
            lessonPositionRepository.deleteById(id);
        }
        return "success";
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

    /**
     * Формирование расписания на день для заданного студента.
     * Именно для студента, поскольку происходит проверка на наличие студента в группах.
     */
    public DayDto formDayDto(UserEntity userEntity, String nameDay, Integer weekNum, Integer numberDay) {

        DayDto dayDto = new DayDto();
        dayDto.setDayName(nameDay);
        // Текущий семестр
        SemesterEntity semesterEntity = semesterRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        DateDto dateDto = dateService.getWeekNow(semesterEntity.getId());
        // рассчёт даты выбранного дня
        LocalDate localDateRequest = dateService.getDayRequest(
                semesterEntity.getUniversity().getWeeks(),
                dateDto.getNumberWeek(),
                dateDto.getNumberDay(),
                weekNum,
                numberDay
        );
        // если это текущий день
        if (localDateRequest.equals(LocalDate.now())) {
            dayDto.setStatus(true);
        }

        String date = localDateRequest.getDayOfMonth() + " " + getNameMonth(localDateRequest.getMonth().getValue());
        dayDto.setDate(date);
        List<InfoLessonDto> infoLessonDtos = lessonPositionRepository
                .findAllForSelectedStudent(weekNum, numberDay, userEntity.getId());

        for (InfoLessonDto less : infoLessonDtos) {
            LessonEntity lessonEntity = lessonRepository.getById(Long.parseLong(less.getGroup()));
            String groups = "";
            for (GroupEntity group : lessonEntity.getGroups()) {
                groups += group.getNumber() + " ";
            }
            less.setGroup(groups);
            notificationService.deleteNotification(lessonPositionRepository.getById(less.getId()));
        }
            dayDto.setInfoLessonDtos(infoLessonDtos);
        return dayDto;
    }

    /**
     * Формирование расписания для преподавателя.
     */
    public DayDto formLessonByProfessor(Long id, String nameDay, Integer weekNum, Integer numberDay) {

        DayDto dayDto = new DayDto();
        dayDto.setDayName(nameDay);
        // Текущий семестр
        SemesterEntity semesterEntity = semesterRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        DateDto dateDto = dateService.getWeekNow(semesterEntity.getId());
        // рассчёт даты выбранного дня
        LocalDate localDateRequest = dateService.getDayRequest(
                semesterEntity.getUniversity().getWeeks(),
                dateDto.getNumberWeek(),
                dateDto.getNumberDay(),
                weekNum,
                numberDay
        );
        // если это текущий день
        if (localDateRequest.equals(LocalDate.now())) {
            dayDto.setStatus(true);
        }
        String date = localDateRequest.getDayOfMonth() + " " + getNameMonth(localDateRequest.getMonth().getValue());
        dayDto.setDate(date);
        List<InfoLessonDto> infoLessonDtos = lessonPositionRepository
                .findAllForSelectedTeacher(weekNum, numberDay, id);

        infoLessonDtos = infoLessonDtos.stream().map(it -> {
            List<GroupEntity> groupEntities = lessonRepository.getById(Long.parseLong(it.getGroup())).getGroups();
            String groups = groupEntities.stream().map(it2 -> {return it2.getNumber() + " ";}).collect(Collectors.toList()).toString();
            groups = groups.substring(1, groups.length() - 2);
            it.setGroup(groups);
            notificationService.deleteNotification(lessonPositionRepository.getById(it.getId()));
            return it;
        }).collect(Collectors.toList());

        dayDto.setInfoLessonDtos(infoLessonDtos);

        return dayDto;
    }

    /**
     * Формирование расписания для группы.
     */
    public DayDto formLessonByGroup(Long id, String nameDay, Integer weekNum, Integer numberDay) {

        DayDto dayDto = new DayDto();
        dayDto.setDayName(nameDay);
        // Текущий семестр
        SemesterEntity semesterEntity = semesterRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        DateDto dateDto = dateService.getWeekNow(semesterEntity.getId());
        // рассчёт даты выбранного дня
        LocalDate localDateRequest = dateService.getDayRequest(
                semesterEntity.getUniversity().getWeeks(),
                dateDto.getNumberWeek(),
                dateDto.getNumberDay(),
                weekNum,
                numberDay
        );
        // если это текущий день
        if (localDateRequest.equals(LocalDate.now())) {
            dayDto.setStatus(true);
        }
        String date = localDateRequest.getDayOfMonth() + " " + getNameMonth(localDateRequest.getMonth().getValue());
        dayDto.setDate(date);
        List<InfoLessonDto> infoLessonDtos = lessonPositionRepository
                .findAllForSelectedGroup(weekNum, numberDay, id);

        infoLessonDtos = infoLessonDtos.stream().map(it -> {
            List<GroupEntity> groupEntities = lessonRepository.getById(Long.parseLong(it.getGroup())).getGroups();
            String groups = groupEntities.stream().map(it2 -> {return it2.getNumber() + " ";}).collect(Collectors.toList()).toString();
            groups = groups.substring(1, groups.length() - 2);
            it.setGroup(groups);
            notificationService.deleteNotification(lessonPositionRepository.getById(it.getId()));
            return it;
        }).collect(Collectors.toList());

        dayDto.setInfoLessonDtos(infoLessonDtos);

        return dayDto;
    }

    /**
     * Формирование расписания для кабинета.
     */
    public DayDto formLessonByClassroom(Long id, String nameDay, Integer weekNum, Integer numberDay) {

        DayDto daysDto = new DayDto();
        daysDto.setDayName(nameDay);
        // Текущий семестр
        SemesterEntity semesterEntity = semesterRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        DateDto dateDto = dateService.getWeekNow(semesterEntity.getId());
        // рассчёт даты выбранного дня
        LocalDate localDateRequest = dateService.getDayRequest(
                semesterEntity.getUniversity().getWeeks(),
                dateDto.getNumberWeek(),
                dateDto.getNumberDay(),
                weekNum,
                numberDay
        );
        // если это текущий день
        if (localDateRequest.equals(LocalDate.now())) {
            daysDto.setStatus(true);
        }
        String date = localDateRequest.getDayOfMonth() + " " + getNameMonth(localDateRequest.getMonth().getValue());
        daysDto.setDate(date);
        List<InfoLessonDto> infoLessonDtos = lessonPositionRepository
                .findAllByForSelectedClassroom(weekNum, numberDay, id);

        infoLessonDtos = infoLessonDtos.stream().map(it -> {
            List<GroupEntity> groupEntities = lessonRepository.getById(Long.parseLong(it.getGroup())).getGroups();
            String groups = groupEntities.stream().map(it2 -> {return it2.getNumber() + " ";}).collect(Collectors.toList()).toString();
            groups = groups.substring(1, groups.length() - 2);
            it.setGroup(groups);
            notificationService.deleteNotification(lessonPositionRepository.getById(it.getId()));
            return it;
        }).collect(Collectors.toList());

        daysDto.setInfoLessonDtos(infoLessonDtos);

        return daysDto;
    }


    public String getNameMonth(int numberMonth) {
        switch (numberMonth) {
            case 1:  return "Января";
            case 2:  return "Февраля";
            case 3:  return "Марта";
            case 4:  return "Апреля";
            case 5:  return "Мая";
            case 6:  return "Июня";
            case 7:  return "Июля";
            case 8:  return "Августа";
            case 9:  return "Сентября";
            case 10:  return "Октября";
            case 11:  return "Ноября";
            default: return "Декабря";
        }
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
}
