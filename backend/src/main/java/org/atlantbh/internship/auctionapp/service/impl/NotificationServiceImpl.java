package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.NotificationEntity;
import org.atlantbh.internship.auctionapp.entity.NotificationType;
import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.atlantbh.internship.auctionapp.entity.UserEntity;
import org.atlantbh.internship.auctionapp.model.Notification;
import org.atlantbh.internship.auctionapp.repository.NotificationRepository;
import org.atlantbh.internship.auctionapp.repository.ProductRepository;
import org.atlantbh.internship.auctionapp.service.api.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SimpMessagingTemplate template;
    private final NotificationRepository notificationRepository;
    private final ProductRepository productRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, ProductRepository productRepository) {
        this.notificationRepository = notificationRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findAllByUserId(userId).stream()
                .map(NotificationEntity::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public List<Notification> getUserUnreadNotifications(Long userId) {
        return notificationRepository.findAllByUserIdAndReadedIsFalse(userId).stream()
                .map(NotificationEntity::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Notification setNotificationAsRead(Long notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId).get();
        notification.setReaded(true);
        notification = notificationRepository.save(notification);
        return notification.toDomainModel();
    }

    @Override
    public void deleteUserNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    @Override
    public void sendNotification(NotificationEntity notification) {
        notificationRepository.save(notification);
        template.convertAndSendToUser(notification.getUser().getEmail(), "/queue", notification.toDomainModel());
    }

    @Scheduled(fixedDelay = 10000)
    public void sendUserWiningNotifications() {
        for (var productBid : productRepository.getEndedProductsWithoutNotification()){
            System.out.println(productBid.getProduct().getName() + " - " + productBid.getBid().getPrice());
            UserEntity user = productBid.getBid().getUser();
            ProductEntity product = productBid.getProduct();
            NotificationEntity notification = new NotificationEntity(user, product, NotificationType.SUCCESS,
                    "Congratulations, you won auction on " + product.getName(), LocalDateTime.now(), false);
            sendNotification(notification);
        }
    }
}
