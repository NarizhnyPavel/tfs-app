package com.TimeForStudy.application.date.service;


import com.TimeForStudy.application.date.model.DateDto;

import java.time.LocalDate;

/**
 * Сервис для работы с временем.
 *
 * @author Velikanov Artyom.
 */
public interface DateService {

    /**
     * Возвращение номера текущей недели.
     *
     * @return номер недели.
     */
    DateDto getWeekNow(long semesterId);

    /**
     * Возвращает день и месяц необходимого запроса дня.
     *
     * @return номер недели.
     */
    LocalDate getDayRequest(int numberWeek, int weekNow, int dayNow, int weekRequest, int dayRequest);

}
