package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.NotificationEntity;
import org.atlantbh.internship.auctionapp.model.Notification;
import org.atlantbh.internship.auctionapp.repository.NotificationRepository;
import org.atlantbh.internship.auctionapp.service.api.NotificationService;
import org.atlantbh.internship.auctionapp.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SimpMessagingTemplate template;
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
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
    public void sendNotification(NotificationEntity notification) {
        notificationRepository.save(notification);
        template.convertAndSendToUser(Jwt.getCurrentUser().getUsername(), "/queue", notification.toDomainModel());
    }
}
