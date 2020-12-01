package com.TimeForStudy.application.date.service;

import com.TimeForStudy.application.date.model.DateDto;

import java.time.LocalDate;

/**
 * Интерфейс сервиса для работы с временем.
 *
 * @author Velikanov Artyom.
 */
public interface DateService {

    /**
     * Получение номера текущей недели и дня
     * относительно начала семестра.
     *
     * @param semesterId идентификатор семестра.
     * @return номер недели и номер дня недели.
     */
    DateDto getWeekNow(Long semesterId);

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
    LocalDate getDayRequest(Integer numberWeek, Integer weekNow, Integer dayNow, Integer weekRequest, Integer dayRequest);

}
