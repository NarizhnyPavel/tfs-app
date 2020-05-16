package com.TimeForStudy.application.date.service;


import com.TimeForStudy.application.date.model.DateDto;

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

}
