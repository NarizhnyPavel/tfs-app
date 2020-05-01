package com.TimeForStudy.application.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий доступа к сущности {@link UserEntity}
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByPhone(String phone);

    List<UserEntity> findAllByRole(Byte role);

}
