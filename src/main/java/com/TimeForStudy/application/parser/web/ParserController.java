package com.TimeForStudy.application.parser.web;

import com.TimeForStudy.application.parser.model.ParserResponse;
import com.TimeForStudy.application.parser.service.ParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
     * Таблица должна располагаться на яндекс диске.
     *
     * @param url публичная ссылка для доступа к таблице.
     * @return строка состояния.
     */
    @PostMapping(value = "/admin/parser")
    public ParserResponse parsNewData(@RequestBody String url) {
        try {
            return parserService.inUrlParser(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
