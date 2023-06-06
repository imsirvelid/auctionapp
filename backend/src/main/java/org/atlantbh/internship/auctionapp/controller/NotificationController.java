package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.model.Notification;
import org.atlantbh.internship.auctionapp.service.api.NotificationService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Notification", description = "Notification APIs")
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(value = "/unread")
    public ResponseEntity<List<Notification>> getUserNotifications(){
        return ResponseEntity.ok(notificationService.getUserUnreadNotifications(Jwt.getCurrentUserId()));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Notification>> getUserAllNotifications() {
        return ResponseEntity.ok(notificationService.getUserNotifications(Jwt.getCurrentUserId()));
    }

    @PostMapping(value = "/read/{id}")
    public ResponseEntity<Notification> setUserNotificationAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.setNotificationAsRead(id));
    }
}
