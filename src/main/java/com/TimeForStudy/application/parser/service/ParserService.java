package com.TimeForStudy.application.parser.service;

import com.TimeForStudy.application.parser.model.ParserResponse;

import java.io.IOException;

/**
 * Сервис для Парсинга таблицы.
 *
 * @author Velikanov Artyom
 */
public interface ParserService {

    /**
     * Парсинг таблицы по url.
     * Таблица должна располагаться на яндекс диске.
     *
     * @param url публичная ссылка для доступа к таблице.
     * @return строка состояния.
     */
    ParserResponse inUrlParser(String url) throws IOException;
}
