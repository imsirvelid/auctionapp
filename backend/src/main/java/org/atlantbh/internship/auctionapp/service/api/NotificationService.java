package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.entity.NotificationEntity;
import org.atlantbh.internship.auctionapp.model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getUserNotifications(Long userId);
    List<Notification> getUserUnreadNotifications(Long userId);
    Notification setNotificationAsRead(Long notificationId);
    void deleteUserNotification(Long notificationId);
    void sendNotification(NotificationEntity notification);
}
