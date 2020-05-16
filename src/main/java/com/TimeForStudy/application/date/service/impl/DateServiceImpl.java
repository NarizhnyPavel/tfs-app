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
 * Сервис для работы с временем
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
     * Возвращение номера текущей недели.
     *
     * @return номер недели.
     */
    @Override
    public DateDto getWeekNow(long semesterId) {

        SemesterEntity semesterEntity = semesterRepository.findById(semesterId)
                .orElseThrow(ErrorDescription.SEMESTER_NOT_FOUNT::exception);
        // Объект для получения номера недели с начала года
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        // Номер недели с начала года недели начала семестра
        int numberWeekStartSemester = semesterEntity.getStart().get(woy);
        // Номер текущей недели
        int numberWeekNow = LocalDate.now().get(woy);
        // Количество недель (делителей) семестра
        int countWeekBySemester = semesterEntity.getUniversity().getWeeks();
        // Номер текущей недели относительно семестра
        int numberWeekBySemester = (numberWeekNow%countWeekBySemester)-(numberWeekStartSemester%countWeekBySemester);
        if (numberWeekBySemester<0) {
            numberWeekBySemester= countWeekBySemester - numberWeekBySemester;
        }
        numberWeekBySemester+=1;
        return new DateDto(numberWeekBySemester, LocalDate.now().getDayOfWeek().getValue());
    }

    /**
     * Возвращает день и месяц необходимого запроса дня.
     *
     * @return номер недели.
     */
    @Override
    public LocalDate getDayRequest(int numberWeek,int weekNow, int dayNow, int weekRequest, int dayRequest) {

        int numberTest = 0;
        if (weekNow>weekRequest) {
            numberTest = (weekRequest + numberWeek - weekNow)*7 - (dayRequest - dayNow);
        } else {
            numberTest = (weekRequest - weekNow)*7 + (dayRequest - dayNow);
        }

        return LocalDate.now().plusDays(numberTest);
    }
}
