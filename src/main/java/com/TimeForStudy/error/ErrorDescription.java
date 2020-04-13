package com.TimeForStudy.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибки приложения.
 *
 * @author Velikanov Artyom.
 */
@Getter
@AllArgsConstructor
public enum  ErrorDescription {
    CLASSROOM_NOT_FOUNT(1, "Кабинет не найден"),
    GROUP_NOT_FOUNT(2, "Группа не найдена"),
    LESSON_NOT_FOUNT(3, "Занятие не найдено"),
    LESSON_GRID_NOT_FOUNT(4, "Место занятия в сетке не найдено"),
    LESSON_TYPE_NOT_FOUNT(5, "Тип занятия не найден"),
    NOTIFICATION_NOT_FOUNT(6, "Уведомление не найдено"),
    SEMESTER_NOT_FOUNT(7, "Семестр не найден"),
    SUBJECT_NOT_FOUNT(8, "Преподаваемая дисциплина не найдена"),
    UNIVERSITY_NOT_FOUNT(9, "Учебное заведение не найдено"),
    USER_NOT_FOUNT(10, "Пользователь не найден"),
    LESSON_POSITION_NOT_FOUNT(11, "Позиция лекции не найдена"),
    MESSAGE_REQUEST_NOT_FOUNT(12, "Запрос на перенос занятия не найден"),
    INCORRECT_DATA_ENTRY(13, "Некорректный ввод данных"),
    DATA_IS_EMPTY(14, "Введённые данные пустые"),
    ACCESS_IS_DENIED(15, "Отказано в доступе. " +
            "У данного пользователя роль с недостаточными для данного действия полномочиями"),
    ROLE_DOES_NOT_MATCH( 16, "Нельзя выставить данную роль для пользователя находящегося в группе"),
    HANDLER_NOT_FOUND(404, "HANDLER_NOT_FOUND"),
    TIMEOUT_ERROR(408, "Истекло время ожидания ответа"),
    VERSION_UNSUPPORTED(469, "Версия не поддерживается"),
    UNKNOWN_ERROR(500, "Неизвестная ошибка сервера"),
    SERVICE_UNAVAILABLE(503, "Сервис недоступен");

    private long code;
    private String message;

    ErrorDescription(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void throwException() throws ApplicationException {
        throw new ApplicationException(this);
    }

    public long getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void throwIfNull(Object obj) {
        if (obj == null) {
            throw new ApplicationException(this);
        }
    }

    public void throwIfTrue(Boolean condition) {
        if (condition) {
            throw new ApplicationException(this);
        }
    }

    public void throwIfFalse(Boolean condition) {
        if (!condition) {
            throw new ApplicationException(this);
        }
    }

    public ApplicationError createApplicationError() {
        return ApplicationError.of(this.code, this.message, null);
    }

    public ApplicationException exception() {
        return new ApplicationException(this);
    }
}