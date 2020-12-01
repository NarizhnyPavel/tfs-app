package com.TimeForStudy.application.user.model;

import lombok.Data;

/**
 * Модель птокена доступа.
 *
 * @author Velikanov Artyom
 */
@Data
public class AuthResponce {

    /**
     * Токен доступа.
     */
    private String authToken;
}
