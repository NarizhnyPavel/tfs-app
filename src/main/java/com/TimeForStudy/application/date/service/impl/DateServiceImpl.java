package com.TimeForStudy.application.date.service.impl;

import com.TimeForStudy.application.date.model.DateDto;
import com.TimeForStudy.application.date.service.DateService;
import com.TimeForStudy.application.semester.domain.SemesterEntity;
import com.TimeForStudy.application.semester.domain.SemesterRepository;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Реализация сервиса для работы с временем.
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class DateServiceImpl implements DateService {

    /**
     * {@link SemesterRepository}
     */
    private final SemesterRepository semesterRepository;

    /**
     * Получение номера текущей недели и дня
     * относительно начала семестра.
     *
     * @param semesterId идентификатор семестра.
     * @return номер недели и номер дня недели.
     */
    @Override
    public DateDto getWeekNow(Long semesterId) {

        SemesterEntity semesterEntity = semesterRepository.findById(semesterId)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        // Объект для получения номера недели с начала года
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        // Номер недели с начала года недели начала семестра
        Integer numberWeekStartSemester = semesterEntity.getStart().get(woy);
        // Номер текущей недели
        Integer numberWeekNow = LocalDate.now().get(woy);
        // Количество недель (делителей) семестра
        Integer countWeekBySemester = semesterEntity.getUniversity().getWeeks();
        // Номер текущей недели относительно семестра
        Integer numberWeekBySemester = (numberWeekNow % countWeekBySemester) - (numberWeekStartSemester % countWeekBySemester);
        if (numberWeekBySemester < 0) {
            numberWeekBySemester = countWeekBySemester - numberWeekBySemester;
        }
        numberWeekBySemester += 1;
        return DateDto.of(numberWeekBySemester, LocalDate.now().getDayOfWeek().getValue());
    }

    /**
     * Получение дня и месяца необходимого запроса дня.
     *
     * @param numberWeek номер недели.
     * @param weekNow номер текущей недели.
     * @param dayNow номер текущего дня.
     * @param weekRequest запрашиваемая неделя.
     * @param dayRequest запрашиваемый день.
     * @return день и месяц.
     */
    @Override
    public LocalDate getDayRequest(Integer numberWeek, Integer weekNow, Integer dayNow, Integer weekRequest, Integer dayRequest) {
        Integer numberTest;
        if (weekNow > weekRequest) {
            numberTest = (weekRequest + numberWeek - weekNow) * 7 + (dayRequest - dayNow);
        } else {
            numberTest = (weekRequest - weekNow) * 7 + (dayRequest - dayNow);
        }
        LocalDate request = LocalDate.now().plusDays(numberTest);
        return request;
    }
}
