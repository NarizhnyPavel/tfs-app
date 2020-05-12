package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.GroupDto;
import com.TimeForStudy.application.lesson.model.AddLessonGroup;
import com.TimeForStudy.application.otherDataClasses.VerificationPair;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.ProfessorDto;
import com.TimeForStudy.application.user.model.UpdateUserDto;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.UserService;
import com.TimeForStudy.error.ErrorDescription;
import lombok.RequiredArgsConstructor;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса CRUD запросов к сущности пользователь
 *
 * @author Velikanov Artyom
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * {@link UserRepository}
     */
    private final UserRepository userRepository;

    /**
     * {@link GroupRepository}
     */
    private final GroupRepository groupRepository;

    /**
     * Возвращение пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    @Override
    public UserDto getUserById(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        return UserDto.of(userEntity);
    }

    /**
     * Возвращает групп пользователя по идентификатору.
     *
     * @param id идентификатор.
     * @return пользователь.
     */
    @Override
    public List<AddLessonGroup> getUserGroupsById(long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        return user.getGroups().stream().map(AddLessonGroup::of).collect(Collectors.toList());
    }

    /**
     * Сохранение пользователя.
     *
     * @param addUserDto пользователь.
     */
    @Override
    public void saveUser(AddUserDto addUserDto) {
        for (GroupDto group : addUserDto.getGroups()) {
            groupRepository.findById(group.getId())
                    .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
        }
        UserEntity userEntity = new UserEntity(addUserDto);
        userRepository.save(userEntity);
    }

    /**
     * Изменение значений пользователя.
     *
     * @param updateUserDto пользователь.
     */
    @Override
    public String updateUser(UpdateUserDto updateUserDto) {
        UserEntity userEntity = userRepository.findById(updateUserDto.getId())
                .orElseThrow(ErrorDescription.USER_NOT_FOUNT::exception);
        userEntity.setPhone(updateUserDto.getPhone());
        userEntity.setName(updateUserDto.getName());
        if (userEntity.getRole() == 4) {
            userEntity.getGroups().clear();
            for (AddLessonGroup group : updateUserDto.getGroups()) {
                GroupEntity updated = groupRepository.findById(group.getId())
                        .orElseThrow(ErrorDescription.GROUP_NOT_FOUNT::exception);
                userEntity.getGroups().add(updated);
                updated.getUsers().add(userEntity);
                groupRepository.save(updated);
            }
        }
        userRepository.save(userEntity);
        return "success";
    }

        /**
         * Удаление пользователя.
         *
         * @param id идентификатор.
         */
        @Override
        public void deleteUser ( long id){
            userRepository.deleteById(id);
        }

        /**
         * Возвращение всех существующих пользователей.
         *
         * @return список пользователей.
         */
        @Override
        public List<UserDto> findAll () {
            List<UserEntity> userEntities = userRepository.findAll();
            return userEntities.stream().map(UserDto::of).collect(Collectors.toList());
        }

        /**
         * Возвращение преподавателей.
         *
         * @return список преподавателей.
         */
        public List<ProfessorDto> findAllProfessors (String name){

            List<UserEntity> userEntities = userRepository.findAllByRole((byte) 2);
            List<ProfessorDto> professorDtos = new ArrayList<>();
            for (UserEntity professor : userEntities) {
                if (professor.getName().contains(name)) {
                    professorDtos.add(new ProfessorDto(professor.getId(), professor.getName()));
                }
            }
            return professorDtos;
        }

    }
