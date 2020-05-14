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
    CLASSROOM_NOT_FOUNT(1, "Кабинет не найден", "111"),
    GROUP_NOT_FOUNT(2, "Группа не найдена", "111"),
    LESSON_NOT_FOUNT(3, "Занятие не найдено", "111"),
    LESSON_GRID_NOT_FOUNT(4, "Место занятия в сетке не найдено", "111"),
    LESSON_TYPE_NOT_FOUNT(5, "Тип занятия не найден", "111"),
    NOTIFICATION_NOT_FOUNT(6, "Уведомление не найдено", "111"),
    SEMESTER_NOT_FOUNT(7, "Семестр не найден", "111"),
    SUBJECT_NOT_FOUNT(8, "Преподаваемая дисциплина не найдена", "111"),
    UNIVERSITY_NOT_FOUNT(9, "Учебное заведение не найдено", "111"),
    USER_NOT_FOUNT(10, "Пользователь не найден", "111"),
    LESSON_POSITION_NOT_FOUNT(11, "Позиция лекции не найдена", "111"),
    MESSAGE_REQUEST_NOT_FOUNT(12, "Запрос на перенос занятия не найден", "111"),
    INCORRECT_DATA_ENTRY(13, "Некорректный ввод данных", "111"),
    DATA_IS_EMPTY(14, "Введённые данные пустые", "111"),
    ACCESS_IS_DENIED(15, "Отказано в доступе. " +
            "У данного пользователя роль с недостаточными для данного действия полномочиями", "111"),
    ROLE_DOES_NOT_MATCH( 16, "Нельзя выставить данную роль для пользователя находящегося в группе", "111"),
    HANDLER_NOT_FOUND(404, "HANDLER_NOT_FOUND", "111"),
    TIMEOUT_ERROR(408, "Истекло время ожидания ответа", "111"),
    VERSION_UNSUPPORTED(469, "Версия не поддерживается", "111"),
    UNKNOWN_ERROR(500, "Неизвестная ошибка сервера", "111"),
    SERVICE_UNAVAILABLE(503, "Сервис недоступен", "111");

    private long code;
    private String message;
    private String info;

    ErrorDescription(int code, String message, String info) {
        this.code = code;
        this.message = message;
        this.info = info;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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