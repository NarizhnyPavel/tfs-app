package com.TimeForStudy.application.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link UserRole}
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}

