package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.entity.NotificationEntity;
import org.atlantbh.internship.auctionapp.entity.NotificationType;
import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.atlantbh.internship.auctionapp.entity.UserEntity;
import org.atlantbh.internship.auctionapp.model.Notification;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.repository.UserRepository;
import org.atlantbh.internship.auctionapp.service.api.NotificationService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Notification", description = "Notification APIs")
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public NotificationController(NotificationService notificationService, UserRepository userRepository, ProductRepository productRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
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

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUserNotification(@PathVariable Long id){
        notificationService.deleteUserNotification(id);
        return ResponseEntity.ok("Notification deleted successfully");
    }

    @PostMapping(value = "/test")
    public ResponseEntity<Boolean> testNotification(@RequestBody String message) {
        UserEntity user = userRepository.findByEmail("velid.imsir@gmail.com").get();
        ProductEntity product = productRepository.findOneRandom();
        NotificationEntity notificationEntity = new NotificationEntity(1L, user, product, NotificationType.INFO, message, LocalDateTime.now(), false);
        notificationService.sendNotification(notificationEntity);
        return ResponseEntity.ok(true);
    }
}
