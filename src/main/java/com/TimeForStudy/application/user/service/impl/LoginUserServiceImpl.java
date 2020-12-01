package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.AuthPair;
import com.TimeForStudy.application.user.model.AuthResponce;
import com.TimeForStudy.application.user.service.LoginUserService;
import com.TimeForStudy.error.ErrorDescription;
import com.TimeForStudy.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * Сервис авторизации пользователя.
 *
 * @author Velikanov Artyom
 * @author Narizhny Pavel
 */
@Service
@RequiredArgsConstructor
public class LoginUserServiceImpl implements LoginUserService {

    /**
     * {@link AuthenticationManager}
     */
    private final AuthenticationManager authenticationManager;
    /**
     * {@link JwtTokenProvider}
     */
    private final JwtTokenProvider jwtTokenProvider;
    /**
     * {@link UserRepository}
     */
    public final UserRepository userRepository;

    /**
     * Авторизация и получение токена доступа
     *
     * @param authPair пара телефон - пароль.
     * @return токен доступа.
     */
    @Override
    public ResponseEntity<AuthResponce> authenticate(AuthPair authPair) {
        try {
            String userPhone = authPair.getPhone();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userPhone, authPair.getPassword()));
            User user = userRepository.findByPhone(userPhone).orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);

            String token = jwtTokenProvider.createToken(userPhone, user.getRole());

            AuthResponce response = new AuthResponce();
            response.setAuthToken(token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Неверное имя пользователя или пароль");
        }
    }
}
