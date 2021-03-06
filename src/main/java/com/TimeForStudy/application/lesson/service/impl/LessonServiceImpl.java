package com.TimeForStudy.application.lesson.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.classroom.domain.ClassroomRepository;
import com.TimeForStudy.application.date.model.DateDto;
import com.TimeForStudy.application.date.service.DateService;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupsDto;
import com.TimeForStudy.application.lesson.domain.LessonEntity;
import com.TimeForStudy.application.lesson.domain.LessonRepository;
import com.TimeForStudy.application.lesson.model.AddLessonGroup;
import com.TimeForStudy.application.lesson.model.BoolLessonDto;
import com.TimeForStudy.application.lesson.model.DayDto;
import com.TimeForStudy.application.lesson.model.GetInfoLessonDto;
import com.TimeForStudy.application.lesson.model.InfoLessonDto;
import com.TimeForStudy.application.lesson.model.LessonByDto;
import com.TimeForStudy.application.lesson.model.LessonEditInfo;
import com.TimeForStudy.application.lesson.model.LessonStopDto;
import com.TimeForStudy.application.lesson.model.NewLessonDto;
import com.TimeForStudy.application.lesson.model.SearchDto;
import com.TimeForStudy.application.lesson.model.UpdatePosition;
import com.TimeForStudy.application.lesson.model.ValidateSearch;
import com.TimeForStudy.application.lesson.service.LessonService;
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
import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.domain.UserRoles;
import com.TimeForStudy.error.ErrorDescription;
import com.TimeForStudy.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ???????????????????? ?????????????? ???????????????? ?? ????????????????.
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
     * {@link UserUtils}
     */
    private final UserUtils userUtils;

    /**
     * ?????????????????? ???????????? ?????? ???????????????? ??????????????.
     *
     * @param id ?????????????????????????? ??????????????.
     * @return ??????????????.
     */
    @Override
    public LessonEditInfo getLessonEdit(Long id) {

        LessonPositionEntity lessonPositionEntity = lessonPositionRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_POSITION_NOT_FOUNT::exception);
        LessonEditInfo lessonEditInfo = new LessonEditInfo();
        lessonEditInfo.setClassroom(lessonPositionEntity.getClassroom().getNumber());
        lessonEditInfo.setClassroomId(lessonPositionEntity.getClassroom().getId());
        lessonEditInfo.setLessonPosition(
                lessonPositionEntity.getWeek() + "" +
                        lessonPositionEntity.getDayNumber() + "" +
                        lessonPositionEntity.getLessonNumber()
        );
        lessonEditInfo.setLessonType(lessonPositionEntity.getLesson().getLessonType().getName());
        lessonEditInfo.setProfessorId(lessonPositionEntity.getLesson().getProfessor().getId());
        lessonEditInfo.setSubjectId(lessonPositionEntity.getLesson().getSubject().getId());
        lessonEditInfo.setGroups(lessonPositionEntity.getLesson().getGroups().stream().map(AddLessonGroup::of).collect(Collectors.toList()));

        return lessonEditInfo;
    }

    /**
     * ???????????? ??????????????.
     *
     * @param lessonStopDto ???????????????????? ?? ??????????????.
     * @return ????????????.
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
                lessonPosition.getDayNumber()
        );
        localDate = localDate.plusDays(1L);
        PositionCancelEntity positionCancelEntity = new PositionCancelEntity();
        positionCancelEntity.setTime(localDate);
        positionCancelEntity.setCancelWeek(lessonStopDto.getWeeks());
        positionCancelEntity.setLessonPositionEntity(lessonPosition);
        positionCancelRepository.save(positionCancelEntity);
        //???????????????????????? ??????????????????????
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setType(false);
        notificationEntity.setLessonPosition(lessonStopDto.getWeeks() +
                "" + lessonPosition.getDayNumber() +
                "" + lessonPosition.getLessonNumber()
        );
        notificationEntity.setSender(lessonPosition.getLesson().getProfessor());
        notificationEntity.setLessons(lessonPosition);
        notificationEntity.setDate(localDate);
        notificationRepository.save(notificationEntity);
        return "success";
    }

    /**
     * ?????????????????? ???????????????????? ?????????????? ?????? ?????????????? ???????????????? ????????????????????????.
     *
     * @param getInfoLessonDto ???????????????????? ?? ??????????????.
     * @return ???????????????????? ??????????????.
     */
    @Override
    public List<DayDto> getLessonInfo(GetInfoLessonDto getInfoLessonDto) {

        //User
        User user = userUtils.getCurrentUser();
        if (user.getRole().getId().equals(UserRoles.TEACHER.getUserRole().getId())) {
            return getLessonBy(new LessonByDto(user.getId(), getInfoLessonDto.getWeekNum(), 1));
        } else {
            //TODO ???????????????? ???????????? ?? ???????????? ????????????????????????
            UniversityEntity universityEntity = universityRepository.findById((long) 1)
                    .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);
            List<DayDto> dayDtos = new ArrayList<>();

            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                dayDtos.add(formDayDto(user, "??????????????????????", getInfoLessonDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                dayDtos.add(formDayDto(user, "??????????????", getInfoLessonDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                dayDtos.add(formDayDto(user, "??????????", getInfoLessonDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                dayDtos.add(formDayDto(user, "??????????????", getInfoLessonDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                dayDtos.add(formDayDto(user, "??????????????", getInfoLessonDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                dayDtos.add(formDayDto(user, "??????????????", getInfoLessonDto.getWeekNum(), 6));
//                daysDtos.add(formDaysDto(groupEntities, "??????????????", addInfoLessonDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                dayDtos.add(formDayDto(user, "??????????????????????", getInfoLessonDto.getWeekNum(), 7));
//                daysDtos.add(formDaysDto(groupEntities, "??????????????????????", addInfoLessonDto.getWeekNum(), 7));
            }
            return dayDtos;
        }
    }

    /**
     * ?????????????????? ???????????????????? ???? ????????????.
     *
     * @param lessonByDto ???????????????????? ?? ??????????????.
     * @return ???????????????????? ??????????????.
     */
    @Override
    public List<DayDto> getLessonBy(LessonByDto lessonByDto) {
        List<DayDto> dayDtos = new ArrayList<>();

        UniversityEntity universityEntity = universityRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.UNIVERSITY_NOT_FOUNT::exception);

        if (lessonByDto.getType() == 1) {
            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "??????????????????????", lessonByDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "??????????", lessonByDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                dayDtos.add(formLessonByProfessor(lessonByDto.getId(), "??????????????????????", lessonByDto.getWeekNum(), 7));
            }
        }
        if (lessonByDto.getType() == 0) {
            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "??????????????????????", lessonByDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "??????????", lessonByDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                dayDtos.add(formLessonByGroup(lessonByDto.getId(), "??????????????????????", lessonByDto.getWeekNum(), 7));
            }
        }
        if (lessonByDto.getType() == 2) {
            if (universityEntity.getWorkDays().indexOf('1') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "??????????????????????", lessonByDto.getWeekNum(), 1));
            }
            if (universityEntity.getWorkDays().indexOf('2') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 2));
            }
            if (universityEntity.getWorkDays().indexOf('3') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "??????????", lessonByDto.getWeekNum(), 3));
            }
            if (universityEntity.getWorkDays().indexOf('4') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 4));
            }
            if (universityEntity.getWorkDays().indexOf('5') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 5));
            }
            if (universityEntity.getWorkDays().indexOf('6') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "??????????????", lessonByDto.getWeekNum(), 6));
            }
            if (universityEntity.getWorkDays().indexOf('7') != -1) {
                dayDtos.add(formLessonByClassroom(lessonByDto.getId(), "??????????????????????", lessonByDto.getWeekNum(), 7));
            }
        }

        return dayDtos;
    }

    /**
     * ?????????????????? ?? ????????????.
     *
     * @param validateSearch ???????????????????????? ?? ??????.
     * @return ???????????? ??????????????????.
     */
    @Override
    //TODO ???????????????? ???? ???????????? ??????????????????????
    public List<SearchDto> getSearch(ValidateSearch validateSearch) {
        List<SearchDto> searchDtos = new ArrayList<>();

        if (validateSearch.getType().isClassroom()) {
            List<ClassroomEntity> classroomEntities = classroomRepository.findAll();

            for (ClassroomEntity classroom : classroomEntities) {
                if (String.valueOf(classroom.getNumber()).contains(validateSearch.getRequest())) {
                    searchDtos.add(SearchDto.of(classroom.getId(), String.valueOf(classroom.getNumber()), 2));
                }
            }
        }
        if (validateSearch.getType().isGroup()) {
            groupRepository.findAll().forEach(group -> {
                if (group.getNumber().toLowerCase().contains(validateSearch.getRequest().toLowerCase())) {
                    searchDtos.add(SearchDto.of(group.getId(), group.getNumber(), 0));
                }
            });

        }
        if (validateSearch.getType().isProfessor()) {
            List<User> teachers = userRepository.findByUserFullNameAndRole(validateSearch.getRequest(), UserRoles.TEACHER.getUserRole());
            searchDtos.addAll(teachers.stream()
                    .map(it -> SearchDto.of(it.getId(), it.getUserInfo().getFullName(), 1))
                    .collect(Collectors.toList()));

        }
        return searchDtos;
    }

    /**
     * ???????????????? ?????????? ???????????????? ?????? ??????????????.
     *
     * @param newLessonDto ??????????????.
     * @return ???????????? ?????????????????? ?????????????????? ????????????.
     */
    @Override
    public List<BoolLessonDto> checkLesson(NewLessonDto newLessonDto) {

        List<BoolLessonDto> boolLessonDtos = new ArrayList<>();
        ClassroomEntity classroom = classroomRepository.findById(newLessonDto.getClassroom())
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        User User = userRepository.findById(newLessonDto.getProfessor())
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        List<GroupEntity> groupEntities = new ArrayList<>();
        newLessonDto.getGroups().forEach(group -> {
            GroupEntity groupEntity = groupRepository.findById(group.getId())
                    .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
            groupEntities.add(groupEntity);
        });
        newLessonDto.getPosition().forEach(positionDto -> {

            BoolLessonDto boolLessonDto = new BoolLessonDto();
            List<GroupsDto> lessonGroups = new ArrayList<>();
            newLessonDto.getGroups().forEach(group -> {
                GroupsDto addLessonGroup = new GroupsDto();
                addLessonGroup.setNumber(1);
                addLessonGroup.setId(group.getId());
                addLessonGroup.setLabel(group.getLabel());
                lessonGroups.add(addLessonGroup);
            });

            boolLessonDto.setGroups(lessonGroups.stream()
                    .map(it -> new AddLessonGroup(it.getId(), it.getLabel(), it.getNumber()))
                    .collect(Collectors.toList()));
            List<LessonPositionEntity> lessonPositionEntities = lessonPositionRepository
                    .findAllByWeekAndDayNumberAndLessonNumber(
                            Integer.parseInt(positionDto.getNum().substring(0, 1)),
                            Integer.parseInt(positionDto.getNum().substring(1, 2)),
                            Integer.parseInt(positionDto.getNum().substring(2, 3))
                    );
            if (Integer.parseInt(positionDto.getNum().substring(0, 1)) != 0) {
                List<LessonPositionEntity> lessonPositionEntities0 = lessonPositionRepository
                        .findAllByWeekAndDayNumberAndLessonNumber(
                                0,
                                Integer.parseInt(positionDto.getNum().substring(1, 2)),
                                Integer.parseInt(positionDto.getNum().substring(2, 3))
                        );
                lessonPositionEntities.addAll(lessonPositionEntities0);
            } else {
                List<LessonPositionEntity> lessonPositionEntities1 = lessonPositionRepository
                        .findAllByWeekAndDayNumberAndLessonNumber(
                                1,
                                Integer.parseInt(positionDto.getNum().substring(1, 2)),
                                Integer.parseInt(positionDto.getNum().substring(2, 3))
                        );
                lessonPositionEntities.addAll(lessonPositionEntities1);
                List<LessonPositionEntity> lessonPositionEntities2 = lessonPositionRepository
                        .findAllByWeekAndDayNumberAndLessonNumber(
                                2,
                                Integer.parseInt(positionDto.getNum().substring(1, 2)),
                                Integer.parseInt(positionDto.getNum().substring(2, 3))
                        );
                lessonPositionEntities.addAll(lessonPositionEntities2);
            }
            lessonPositionEntities.forEach(position -> {
                //???????????????? ????????????????
                if (position.getClassroom().equals(classroom)) {
                    boolLessonDto.setClassroom(0);
                }
                //???????????????? ??????????????????????????
                if (position.getLesson().getProfessor().equals(User)) {
                    boolLessonDto.setProfessor(0);
                }
                //???????????????? ??????????
                int i = 0;
                for (AddLessonGroup group1 : boolLessonDto.getGroups()) {
                    if (position.getLesson().getGroups().contains(groupEntities.get(i))) {
                        group1.setNumber(0);
                    }
                    i++;
                }
            });
            boolLessonDto.setPosition(positionDto.getNum());
            boolLessonDtos.add(boolLessonDto);
        });
        return boolLessonDtos;
    }

    /**
     * ?????????????????? ?????????? ??????????????.
     *
     * @param newLessonDto ??????????????.
     * @return ???????????? ???????????????????? ??????????????.
     */
    @Override
    public String addLesson(NewLessonDto newLessonDto) {

        ClassroomEntity classroom = classroomRepository.findById(newLessonDto.getClassroom())
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        User User = userRepository.findById(newLessonDto.getProfessor())
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        SemesterEntity semesterEntity = semesterRepository.findById((long) 1) //???????????????????? ?????? ????????????????
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        SubjectEntity subjectEntity = subjectRepository.findById(newLessonDto.getSubject())
                .orElseThrow(ErrorDescription.SUBJECT_NOT_FOUNT::exception);
        List<GroupEntity> groupEntities = new ArrayList<>();
        newLessonDto.getGroups().forEach(group -> {
            GroupEntity groupEntity = groupRepository.findById(group.getId())
                    .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
            groupEntities.add(groupEntity);
        });
        LessonTypeEntity lessonTypeEntity = lessonTypeRepository.findById(newLessonDto.getLessonType())
                .orElseThrow(ErrorDescription.LESSON_TYPE_NOT_FOUNT::exception);
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setGroups(groupEntities);
        lessonEntity.setLessonType(lessonTypeEntity);
        lessonEntity.setSemester(semesterEntity);
        lessonEntity.setProfessor(User);
        lessonEntity.setClassroom(classroom);
        lessonEntity.setSubject(subjectEntity);
        lessonRepository.save(lessonEntity);
        newLessonDto.getPosition().forEach(positionDto -> {
            LessonPositionEntity lessonPositionEntity = new LessonPositionEntity();
            lessonPositionEntity.setClassroom(classroom);
            lessonPositionEntity.setDayNumber(Integer.parseInt(positionDto.getNum().substring(1, 2)));
            lessonPositionEntity.setWeek(Integer.parseInt(positionDto.getNum().substring(0, 1)));
            lessonPositionEntity.setLessonNumber(Integer.parseInt(positionDto.getNum().substring(2, 3)));
            lessonPositionEntity.setLesson(lessonEntity);
            lessonPositionRepository.save(lessonPositionEntity);
        });
        return "success";
    }

    /**
     * ?????????????? ??????????????.
     *
     * @param updatePosition ???????????? ?????? ????????????????.
     * @return ???????????? ???????????????? ??????????????.
     */
    @Override
    public String updateLesson(UpdatePosition updatePosition) {

        LessonPositionEntity lessonPositionEntity = lessonPositionRepository
                .findById(updatePosition.getOldPositionId())
                .orElseThrow(ErrorDescription.LESSON_POSITION_NOT_FOUNT::exception);
        ClassroomEntity classroomEntity = classroomRepository.findById(updatePosition.getNewClassroomId())
                .orElseThrow(ErrorDescription.CLASSROOM_NOT_FOUNT::exception);
        lessonPositionEntity.setClassroom(classroomEntity);
        lessonPositionEntity.setWeek(Integer.parseInt(updatePosition.getNewPositionNum().substring(0, 1)));
        lessonPositionEntity.setDayNumber(Integer.parseInt(updatePosition.getNewPositionNum().substring(1, 2)));
        lessonPositionEntity.setLessonNumber(Integer.parseInt(updatePosition.getNewPositionNum().substring(2, 3)));
        lessonPositionRepository.save(lessonPositionEntity);

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setType(true);
        notificationEntity.setLessonPosition(updatePosition.getNewPositionNum());
        notificationEntity.setSender(lessonPositionEntity.getLesson().getProfessor());
        notificationEntity.setLessons(lessonPositionEntity);

        // ?????????????? ??????????????
        SemesterEntity semesterEntity = semesterRepository.findById((long) 1)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        DateDto dateDto = dateService.getWeekNow(semesterEntity.getId());
        // ?????????????? ???????? ???????????????????? ??????
        LocalDate localDateRequest = dateService.getDayRequest(
                semesterEntity.getUniversity().getWeeks(),
                dateDto.getNumberWeek(),
                dateDto.getNumberDay(),
                Integer.parseInt(updatePosition.getNewPositionNum().substring(0, 1)),
                Integer.parseInt(updatePosition.getNewPositionNum().substring(1, 2))
        );
        localDateRequest = localDateRequest.plusDays(1);
        notificationEntity.setDate(localDateRequest);
        notificationRepository.save(notificationEntity);
        return "success";
    }

    /**
     * ???????????????? ??????????????.
     *
     * @param id ?????????????????????????? ??????????????.
     * @return ???????????? ???????????????? ??????????????.
     */
    @Override
    public String deleteLesson(Long id) {
        LessonPositionEntity lessonPositionEntity = lessonPositionRepository.findById(id)
                .orElseThrow(ErrorDescription.LESSON_POSITION_NOT_FOUNT::exception);
        if (lessonPositionEntity.getLesson().getLessonPositions().size() == 1) {
            lessonPositionRepository.deleteById(id);
            lessonRepository.deleteById(lessonPositionEntity.getLesson().getId());
        } else {
            lessonPositionRepository.deleteById(id);
        }
        return "success";
    }

    /**
     * ???????????????????????? ???????????????????? ???? ???????? ?????? ?????????????????? ????????????????.
     * ???????????? ?????? ????????????????, ?????????????????? ???????????????????? ???????????????? ???? ?????????????? ???????????????? ?? ??????????????.
     *
     * @param User ??????????????.
     * @param nameDay ???????????????????????? ??????.
     * @param weekNum ?????????? ????????????.
     * @param numberDay ?????????? ??????.
     * @return ???????????? ??????.
     */
    public DayDto formDayDto(User User, String nameDay, Integer weekNum, Integer numberDay) {

        DayDto dayDto = getInitialDayDto(nameDay, weekNum, numberDay);
        List<InfoLessonDto> infoLessonDtos = lessonPositionRepository
                .findAllForSelectedStudent(weekNum, numberDay, User.getId());
        infoLessonDtos.forEach(less -> {
            LessonEntity lessonEntity = lessonRepository.getById(Long.parseLong(less.getGroup()));
            StringBuilder groups = new StringBuilder();
            lessonEntity.getGroups().forEach(group -> {
                groups.append(group.getNumber()).append(" ");
            });
            less.setGroup(groups.toString());
            notificationService.deleteNotification(lessonPositionRepository.getById(less.getId()));
        });

        dayDto.setInfoLessonDtos(infoLessonDtos);
        return dayDto;
    }

    /**
     *  ?????????????????? ???????????? ?????? ???? ??????????????????.
     *
     * @param nameDay ???????????????????????? ??????.
     * @param weekNum ?????????? ????????????.
     * @param numberDay ?????????? ??????.
     * @return ???????????? ??????.
     */
    private DayDto getInitialDayDto(String nameDay, Integer weekNum, Integer numberDay) {
        DayDto dayDto = new DayDto();
        dayDto.setDayName(nameDay);
        // ?????????????? ??????????????
        SemesterEntity semesterEntity = semesterRepository.findById(1L)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        DateDto dateDto = dateService.getWeekNow(semesterEntity.getId());
        // ?????????????? ???????? ???????????????????? ??????
        LocalDate localDateRequest = dateService.getDayRequest(
                semesterEntity.getUniversity().getWeeks(),
                dateDto.getNumberWeek(),
                dateDto.getNumberDay(),
                weekNum,
                numberDay
        );
        // ???????? ?????? ?????????????? ????????
        if (LocalDate.now().equals(localDateRequest)) {
            dayDto.setStatus(true);
        }
        String date = localDateRequest.getDayOfMonth() + " " + getNameMonth(localDateRequest.getMonth().getValue());
        dayDto.setDate(date);
        return dayDto;
    }

    /**
     * ???????????????????????? ???????????????????? ?????? ??????????????????????????.
     *
     * @param id ??????????????????????????.
     * @param nameDay ???????????????????????? ??????.
     * @param weekNum ?????????? ????????????.
     * @param numberDay ?????????? ??????.
     * @return ???????????? ??????.
     */
    public DayDto formLessonByProfessor(Long id, String nameDay, Integer weekNum, Integer numberDay) {

        DayDto dayDto = getInitialDayDto(nameDay, weekNum, numberDay);
        List<InfoLessonDto> infoLessonDtos = lessonPositionRepository
                .findAllForSelectedTeacher(weekNum, numberDay, id);

        infoLessonDtos = infoLessonDtos.stream().peek(this::getGroupEntities).collect(Collectors.toList());

        dayDto.setInfoLessonDtos(infoLessonDtos);

        return dayDto;
    }

    /**
     * ???????????????????????? ???????????????????? ?????? ????????????.
     *
     * @param id ??????????????????????????.
     * @param nameDay ???????????????????????? ??????.
     * @param weekNum ?????????? ????????????.
     * @param numberDay ?????????? ??????.
     * @return ???????????? ??????.
     */
    public DayDto formLessonByGroup(Long id, String nameDay, Integer weekNum, Integer numberDay) {

        DayDto dayDto = getInitialDayDto(nameDay, weekNum, numberDay);
        List<InfoLessonDto> infoLessonDtos = lessonPositionRepository
                .findAllForSelectedGroup(weekNum, numberDay, id);

        infoLessonDtos = infoLessonDtos.stream().peek(this::getGroupEntities).collect(Collectors.toList());

        dayDto.setInfoLessonDtos(infoLessonDtos);

        return dayDto;
    }

    /**
     * ???????????????????????? ???????????????????? ?????? ????????????????.
     *
     * @param id ??????????????????????????.
     * @param nameDay ???????????????????????? ??????.
     * @param weekNum ?????????? ????????????.
     * @param numberDay ?????????? ??????.
     * @return ???????????? ??????.
     */
    public DayDto formLessonByClassroom(Long id, String nameDay, Integer weekNum, Integer numberDay) {

        DayDto dayDto = getInitialDayDto(nameDay, weekNum, numberDay);
        List<InfoLessonDto> infoLessonDtos = lessonPositionRepository
                .findAllByForSelectedClassroom(weekNum, numberDay, id);

        infoLessonDtos = infoLessonDtos.stream().peek(this::getGroupEntities).collect(Collectors.toList());

        dayDto.setInfoLessonDtos(infoLessonDtos);

        return dayDto;
    }

    /**
     * ?????????????????? ???????????????? ????????????.
     *
     * @param numberMonth ?????????? ????????????.
     * @return ???????????????? ????????????.
     */
    public String getNameMonth(Integer numberMonth) {
        switch (numberMonth) {
            case 1:
                return "????????????";
            case 2:
                return "??????????????";
            case 3:
                return "??????????";
            case 4:
                return "????????????";
            case 5:
                return "??????";
            case 6:
                return "????????";
            case 7:
                return "????????";
            case 8:
                return "??????????????";
            case 9:
                return "????????????????";
            case 10:
                return "??????????????";
            case 11:
                return "????????????";
            default:
                return "??????????????";
        }
    }

    //TODO ??????-???? ???? ?????? ?? ???????? ?????????????? (??????????????????)
    private void getGroupEntities(InfoLessonDto infoLessonDto) {
        List<GroupEntity> groupEntities = lessonRepository.getById(Long.parseLong(infoLessonDto.getGroup())).getGroups();
        String groups = groupEntities.stream().map(it2 -> it2.getNumber() + " ").collect(Collectors.toList()).toString();
        groups = groups.substring(1, groups.length() - 2);
        infoLessonDto.setGroup(groups);
        notificationService.deleteNotification(lessonPositionRepository.getById(infoLessonDto.getId()));
    }

}
