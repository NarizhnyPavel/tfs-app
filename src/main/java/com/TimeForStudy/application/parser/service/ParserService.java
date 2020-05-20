package com.TimeForStudy.application.parser.service;

import com.TimeForStudy.application.parser.domain.ParserResponse;

import java.io.IOException;

/**
 * Сервис для Парсинга таблицы.
 *
 * @author Velikanov Artyom
 */
public interface ParserService {

    /**
     * Получение url ссылки на парсикг
     */
    ParserResponse inUrlParser(String url) throws IOException;
}
