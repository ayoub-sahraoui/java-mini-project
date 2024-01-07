package com.hotelbooking.hotelbooking.modules.notification.repository;

import com.hotelbooking.hotelbooking.modules.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
