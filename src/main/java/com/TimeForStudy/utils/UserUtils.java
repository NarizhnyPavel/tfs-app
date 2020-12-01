package com.TimeForStudy.utils;

import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.error.ErrorDescription;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Утилитарный класс пользователя.
 *
 * @author Velikanov Artyom.
 */
@Service
@AllArgsConstructor
public class UserUtils {

    private final UserRepository userRepository;

    /**
     * Получение информации о текущем авторизованном пользователе.
     *
     * @return информации о текущем авторизованном пользователе.
     */
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object credentials = authentication.getCredentials();
        if(!(credentials instanceof String)) {
            return null;
        }

        String username = authentication.getName();

        return userRepository.findByPhone(username).orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
    }

    /**
     * Проверка телефона на наличие в системе
     *
     * @param phone телефон
     * @return true - такой телефон уже зарегистрирован false - телефон не зареган
     */
    public boolean checkUniqPhone(String phone){
        return userRepository.findByPhone(phone).isPresent(); }

    /**
     * Проверка корректности телефона
     *
     * @param phone телефон
     * @return true - телефон корректен false - некорректный
     */
    public boolean checkPhoneFormat(String phone){
        String pattern = "^([87])\\d{10}";
        return phone.matches(pattern);
    }

}
