package com.TimeForStudy.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ошибка приложения.
 *
 * @author Velikanov Artyom.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ApplicationError {

    /**
     * Код ошибки.
     */
    private long code;
    /**
     * Сообщение ошибки.
     */
    private String message;
    /**
     * Стектрейс.
     */
    private String cause;

}
