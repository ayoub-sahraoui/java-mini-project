package com.hotelbooking.hotelbooking.modules.notification.controller;

import com.hotelbooking.hotelbooking.modules.notification.model.NotificationDTO;
import com.hotelbooking.hotelbooking.modules.notification.model.CreateNotificationDTO;
import com.hotelbooking.hotelbooking.modules.notification.model.UpdateNotificationDTO;
import com.hotelbooking.hotelbooking.modules.notification.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.notification.service.NotificationService;
import com.hotelbooking.hotelbooking.modules.notification.exception.NotificationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController extends ResponseEntityExceptionHandler {

    @Autowired
    private NotificationService notificationService;

    // Get all notifications
    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        List<NotificationDTO> notifications = notificationService.getAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    // Get a specific notification by ID
    @GetMapping("/{notificationId}")
    public ResponseEntity<Object> getNotificationById(@PathVariable Long notificationId) {
        try {
            NotificationDTO notification = notificationService.getNotificationById(notificationId);
            return new ResponseEntity<>(new APIResponse("success", "Notification retrieved successfully", notification), HttpStatus.OK);
        } catch (NotificationNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new notification
    @PostMapping
    public ResponseEntity<Object> createNotification(@RequestBody CreateNotificationDTO notification) {
        try {
            NotificationDTO createdNotification = notificationService.createNotification(notification);
            return new ResponseEntity<>(new APIResponse("success", "Notification created successfully", createdNotification), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing notification
    @PutMapping("/{notificationId}")
    public ResponseEntity<Object> updateNotification(@PathVariable Long notificationId, @RequestBody UpdateNotificationDTO notification) {
        try {
            NotificationDTO updatedNotification = notificationService.updateNotification(notificationId, notification);
            return new ResponseEntity<>(new APIResponse("success", "Notification updated successfully", updatedNotification), HttpStatus.OK);
        } catch (NotificationNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a notification by ID
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Object> deleteNotification(@PathVariable Long notificationId) {
        try {
            notificationService.deleteNotification(notificationId);
            return new ResponseEntity<>(new APIResponse("success", "Notification deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (NotificationNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
