package com.hotelbooking.hotelbooking.modules.notification.service;

import com.hotelbooking.hotelbooking.modules.notification.model.NotificationDTO;
import com.hotelbooking.hotelbooking.modules.notification.model.CreateNotificationDTO;
import com.hotelbooking.hotelbooking.modules.notification.model.UpdateNotificationDTO;

import com.hotelbooking.hotelbooking.modules.notification.model.Notification;
import com.hotelbooking.hotelbooking.modules.notification.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.notification.repository.NotificationRepository;
import com.hotelbooking.hotelbooking.modules.notification.exception.NotificationNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional(readOnly = true)
    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(notification -> EntityDTOConverter.convertToDTO(notification, NotificationDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NotificationDTO getNotificationById(Long notificationId) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        if (optionalNotification.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalNotification.get(), NotificationDTO.class);
        } else {
            throw new NotificationNotFoundException("Notification not found with ID: " + notificationId);
        }
    }

    @Transactional
    public NotificationDTO createNotification(CreateNotificationDTO notificationDTO) {
        Notification newNotification = EntityDTOConverter.convertToEntity(notificationDTO, Notification.class);
        Notification savedNotification = notificationRepository.save(newNotification);
        return EntityDTOConverter.convertToDTO(savedNotification, NotificationDTO.class);
    }

    @Transactional
    public NotificationDTO updateNotification(Long notificationId, UpdateNotificationDTO notificationDTO) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        if (optionalNotification.isPresent()) {
            Notification existingNotification = optionalNotification.get();
            BeanUtils.copyProperties(notificationDTO, existingNotification, "id");
            Notification updatedNotification = notificationRepository.save(existingNotification);
            return EntityDTOConverter.convertToDTO(updatedNotification, NotificationDTO.class);
        }
        else{
            throw new NotificationNotFoundException("Notification not found with ID: " + notificationId);
        }
    }

    @Transactional
    public void deleteNotification(Long notificationId) {
        if (notificationRepository.existsById(notificationId)) {
            notificationRepository.deleteById(notificationId);
        }
        else{
            throw new NotificationNotFoundException("Notification not found with ID: " + notificationId);
        }
    }
}
