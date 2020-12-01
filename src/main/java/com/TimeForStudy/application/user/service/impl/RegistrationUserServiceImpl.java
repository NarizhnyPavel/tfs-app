package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupsDto;
import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.domain.UserInfo;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.domain.UserRoles;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.service.RegistrationUserService;
import com.TimeForStudy.error.ApplicationException;
import com.TimeForStudy.error.ErrorDescription;
import com.TimeForStudy.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса регистрации пользователя.
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationUserServiceImpl implements RegistrationUserService {

    /**
     * {@link UserRepository}
     */
    public final UserRepository userRepository;
    /**
     * {@link GroupRepository}
     */
    public final GroupRepository groupRepository;
    /**
     * {@link UserUtils}
     */
    private final UserUtils userUtils;
    /**
     * {@link BCryptPasswordEncoder}
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Регистрация пользователя.
     *
     * @param registerDto запрос на авторизацию.
     */
    @Override
    public void registerUser(AddUserDto registerDto) {
        log.info("invoke registerUser({})", registerDto);
        User user = new User();
        if (!userUtils.checkPhoneFormat(registerDto.getPhone()))
            throw new ApplicationException(ErrorDescription.PHONE_HAS_INCORRECT_FORMAT);
        if (userUtils.checkUniqPhone(registerDto.getPhone()))
            throw new ApplicationException(ErrorDescription.PHONE_ALREADY_REGISTERED);
        user.setPhone(registerDto.getPhone());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(UserRoles.STUDENT.getUserRole());
        user.setLastUpdateDate(new Date());
        UserInfo info = new UserInfo();
        info.setFirstName(registerDto.getFirstName());
        info.setLastName(registerDto.getLastName());
        info.setPatronymic(registerDto.getPatronymic());
        user.setUserInfo(info);
        if (!registerDto.getGroups().isEmpty()) {
            List<String> numbers = registerDto.getGroups().stream().map(GroupsDto::getLabel).collect(Collectors.toList());
            List<GroupEntity> groups = groupRepository.findAllByNumber(numbers);
            user.getGroups().addAll(groups);
        }
        userRepository.save(user);
        log.info("result registerUser(): user {} created", user);
    }
}