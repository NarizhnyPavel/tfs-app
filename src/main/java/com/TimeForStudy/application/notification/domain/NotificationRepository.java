package com.TimeForStudy.application.notification.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link NotificationEntity}
 */
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
