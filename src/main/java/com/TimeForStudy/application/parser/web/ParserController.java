package com.TimeForStudy.application.parser.web;

import com.TimeForStudy.application.parser.service.ParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Обработчик запросов для парсинга.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class ParserController {

    /**
     * {@link ParserService}
     */
    private final ParserService parserService;

    /**
     * Парсинг таблицы по url.
     *
     * @return строка состояния.
     */
    @PostMapping(value = "/parser/url")
    public String getProfessors(@RequestBody String url) {
        return parserService.inUrlParser(url);
    }

}
