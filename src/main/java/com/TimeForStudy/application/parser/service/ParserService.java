package com.TimeForStudy.application.parser.service;

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
    String inUrlParser(String url) throws IOException;
}
