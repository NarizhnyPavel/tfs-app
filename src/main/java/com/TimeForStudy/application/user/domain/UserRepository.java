package com.TimeForStudy.application.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий доступа к сущности {@link User}
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Поиск по подстроке полного имени пользователя.
     *
     * @param name подстрока.
     * @return пользователь.
     */
    @Query("from User u left join UserInfo i on i.id = u.userInfo.id " +
            "where lower(concat(i.firstName, ' ', i.lastName, ' ', i.patronymic)) like lower(concat('%', :name, '%'))")
    List<User> findByUserFullName(@Param("name") String name);

    /**
     * Поиск по подстроке полного имени пользователя и роли.
     *
     * @param name подстрока.
     * @param role роль пользователя.
     * @return пользователь.
     */
    @Query("from User u left join UserInfo i on i.id = u.userInfo.id " +
            "where lower(concat(i.firstName, ' ', i.lastName, ' ', i.patronymic)) like lower(concat('%', :name, '%')) " +
            "and u.role = :role")
    List<User> findByUserFullNameAndRole(@Param("name") String name, @Param("role") UserRole role);

    /**
     * Поиск пользователя по телефону.
     *
     * @param phone номер телефона.
     * @return пользователь.
     */
    Optional<User> findByPhone(String phone);

    /**
     * Поиск всех пользователей определённой роли.
     *
     * @param userRole роль пользоателя.
     * @return список пользователей.
     */
    List<User> findAllByRole(UserRoles userRole);

}
