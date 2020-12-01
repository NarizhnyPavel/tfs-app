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
    CLASSROOM_NOT_FOUNT(1, "Кабинет не найден", ""),
    GROUP_NOT_FOUNT(2, "Группа не найдена", ""),
    LESSON_NOT_FOUNT(3, "Занятие не найдено", ""),
    LESSON_GRID_NOT_FOUNT(4, "Место занятия в сетке не найдено", ""),
    LESSON_TYPE_NOT_FOUNT(5, "Тип занятия не найден", ""),
    NOTIFICATION_NOT_FOUNT(6, "Уведомление не найдено", ""),
    SEMESTER_NOT_FOUNT(7, "Семестр не найден", ""),
    SUBJECT_NOT_FOUNT(8, "Преподаваемая дисциплина не найдена", ""),
    UNIVERSITY_NOT_FOUNT(9, "Учебное заведение не найдено", ""),
    USER_NOT_FOUNT(10, "Пользователь не найден", ""),
    LESSON_POSITION_NOT_FOUNT(11, "Позиция лекции не найдена", ""),
    MESSAGE_REQUEST_NOT_FOUNT(12, "Запрос на перенос занятия не найден", ""),
    INCORRECT_DATA_ENTRY(13, "Некорректный ввод данных", ""),
    DATA_IS_EMPTY(14, "Введённые данные пустые", ""),
    ACCESS_IS_DENIED(15, "Access is denied because role authorities", ""),
    ROLE_DOES_NOT_MATCH( 16, "Нельзя выставить данную роль для пользователя находящегося в группе", ""),
    GROUP_HAS_STUDENTS(17, "В данную группу входят учащиеся", ""),
    CLASSROOM_HAS_LESSONS(18, "В данной аудитории проводятся занятия", ""),
    PHONE_ALREADY_REGISTERED(19, "Пользователь с таким номером телефона уже зарегистрирован", ""),
    PHONE_HAS_INCORRECT_FORMAT(20, "У телефона неверный формат", ""),
    HANDLER_NOT_FOUND(4014, "HANDLER_NOT_FOUND", ""),
    ACCESS_FORBIDDEN(403, "Unauthorized access", ""),
    TIMEOUT_ERROR(408, "Истекло время ожидания ответа", ""),
    VERSION_UNSUPPORTED(469, "Версия не поддерживается", ""),
    UNKNOWN_ERROR(500, "Неизвестная ошибка сервера", ""),
    SERVICE_UNAVAILABLE(503, "Сервис недоступен", "");

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