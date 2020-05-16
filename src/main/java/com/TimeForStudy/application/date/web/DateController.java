package com.TimeForStudy.application.date.web;

import com.TimeForStudy.application.date.model.DateDto;
import com.TimeForStudy.application.date.service.DateService;
import com.TimeForStudy.application.group.model.GroupsDto;
import com.TimeForStudy.application.group.service.GroupService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * Возвращает список группы.
     *
     * @return список группы.
     */
    @GetMapping(value = "/week/now/{id}")
    public DateDto postGroups(@PathVariable long id) {
        return dateService.getWeekNow(id);
    }


}
