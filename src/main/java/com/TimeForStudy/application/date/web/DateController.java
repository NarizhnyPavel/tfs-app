package com.TimeForStudy.application.date.web;

import com.TimeForStudy.application.date.model.DateDto;
import com.TimeForStudy.application.date.service.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Обработчик запросов для определения даты.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class DateController {

    /**
     * {@link DateService}
     */
    private final DateService dateService;

    /**
     * Получение номера текущей недели и дня
     * относительно начала семестра.
     *
     * @param id идентификатор семестра.
     * @return номер недели и номер дня недели.
     */
    @GetMapping(value = "/week/now/{id}")
    public DateDto postGroups(@PathVariable Long id) {
        return dateService.getWeekNow(id);
    }


}
