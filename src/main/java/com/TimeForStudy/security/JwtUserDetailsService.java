package com.TimeForStudy.security;

import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.service.UserService;
import com.TimeForStudy.security.jwt.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    /**
     * {@link UserService}
     */
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByPhone(s);
        return JwtUserFactory.create(user);
    }

}
