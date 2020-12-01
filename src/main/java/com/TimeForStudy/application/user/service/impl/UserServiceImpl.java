package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.lesson.model.AddLessonGroup;
import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.user.domain.UserInfo;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.domain.UserRoleRepository;
import com.TimeForStudy.application.user.domain.UserRoles;
import com.TimeForStudy.application.user.model.RoleDto;
import com.TimeForStudy.application.user.model.UpdateUserDto;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.UserService;
import com.TimeForStudy.error.ErrorDescription;
import com.TimeForStudy.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса запросов к пользователям.
 *
 * @author Velikanov Artyom.
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * {@link UserRepository}
     */
    private final UserRepository userRepository;
    /**
     * {@link UserUtils}
     */
    private final UserUtils userUtils;
    /**
     * {@link UserRoleRepository}
     */
    private final UserRoleRepository userRoleRepository;
    /**
     * {@link GroupRepository}
     */
    private final GroupRepository groupRepository;

    /**
     * Получение списка пользователей.
     *
     * @return список пользователей.
     */
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(it -> {
                    UserDto dto = new UserDto();
                    dto.setId(it.getId());
                    dto.setFirstName(it.getUserInfo().getFirstName());
                    dto.setLastName(it.getUserInfo().getLastName());
                    dto.setPatronymic(it.getUserInfo().getPatronymic());
                    dto.setUserRole(RoleDto.of(it.getRole().getId(),
                            it.getRole().getName(), it.getRole().getRank()));
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * Поиск по ФИО пользователей.
     *
     * @param userName подстрока, по которой осуществляется поиск.
     * @return список пользователей.
     */
    @Override
    public List<UserDto> findByUserName(String userName) {
        return userRepository.findByUserFullName(userName).stream()
                .map(UserDto::of).collect(Collectors.toList());
    }

    /**
     * Получение пользователя по телефону
     *
     * @param phone номер телефона.
     * @return пользователь.
     */
    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone).
                orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
    }

    /**
     * Поиск преподавателей с поиском по подстроке в имени.
     *
     * @param name подстрока.
     * @return список преподвателей.
     */
    @Override
    public List<IdNameDto> getProfessors(String name) {
        return userRepository.findByUserFullNameAndRole(name, UserRoles.TEACHER.getUserRole()).stream()
                .map(it -> IdNameDto.of(it.getId(), it.getUserInfo().getFullName()))
                .collect(Collectors.toList());
    }

    /**
     * Получение списка групп пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return список групп.
     */
    @Override
    public List<AddLessonGroup> getUserGroups(Long id) {
        User user = userRepository.findById(id).orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        return user.getGroups().stream()
                .map(it -> new AddLessonGroup(it.getId(),
                        it.getNumber(), Integer.parseInt(it.getNumber()))
                ).collect(Collectors.toList());
    }

    /**
     * Получение списка ролей пользователя.
     *
     * @return список ролей.
     */
    @Override
    public List<RoleDto> getUserRoles() {
        return userRoleRepository.findAll().stream()
                .map(it -> RoleDto.of(it.getId(), it.getName(), it.getRank()))
                .collect(Collectors.toList());
    }

    /**
     * Редактирование информацию о текущем пользователе.
     *
     * @param updateUserDto пользователь.
     */
    @Override
    public void updateCurrentUser(UpdateUserDto updateUserDto) {
        log.info("invoke updateUser({})", updateUserDto);
        User user = userUtils.getCurrentUser();
        user.setLastUpdateDate(new Date());
        UserInfo info = user.getUserInfo();
        info.setFirstName(updateUserDto.getFirstName());
        info.setLastName(updateUserDto.getLastName());
        info.setPatronymic(updateUserDto.getPatronymic());
        user.setUserInfo(info);
        List<GroupEntity> groups = groupRepository.findAllByNumber(updateUserDto.getGroups().stream()
                .map(AddLessonGroup::getLabel).collect(Collectors.toList()));
        user.setGroups(groups);
        userRepository.save(user);
        log.info("result updateUser({}): saved", updateUserDto);
    }

    /**
     * Смена роли пользователя.
     *
     * @param userId  идентификатор пользователя.
     * @param newRole модель выбранной роли.
     */
    @Override
    public void changeUserRole(Long userId, RoleDto newRole) {
        log.info("invoke changeUserRole({}, {})", userId, newRole);
        User user = userRepository.findById(userId).orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        user.setRole(UserRoles.valueOf(newRole.getName()).getUserRole());
        userRepository.save(user);
        log.info("result changeUserRole(): role for user ({}), changed to {}", user, newRole.getName());
    }

    /**
     * Получение пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getUserInfo().getFirstName());
        dto.setLastName(user.getUserInfo().getLastName());
        dto.setPatronymic(user.getUserInfo().getPatronymic());
        dto.setUserRole(RoleDto.of(user.getRole().getId(),
                user.getRole().getName(), user.getRole().getRank()));
        return dto;
    }

    /**
     * Удаление пользователя.
     *
     * @param id идентификатор.
     */
    @Override
    public void deleteUser(Long id) {
        log.info("invoke deleteUser({})", id);
        User user = userRepository.findById(id).orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        //TODO проверка что это преподаватель
        userRepository.delete(user);
        log.info("result deleteUser({}): user {} deleted", id, user.getUserInfo().getFullName());
    }
}
