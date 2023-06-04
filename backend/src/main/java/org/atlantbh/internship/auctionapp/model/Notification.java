package org.atlantbh.internship.auctionapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atlantbh.internship.auctionapp.entity.NotificationType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private User user;
    private Product product;
    private NotificationType type;
    private String message;
    private LocalDateTime date;
    private Boolean readed;
}
