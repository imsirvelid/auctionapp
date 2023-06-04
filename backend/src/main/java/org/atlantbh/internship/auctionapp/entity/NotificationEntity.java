package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.atlantbh.internship.auctionapp.model.Notification;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "readed")
    private Boolean readed;

    public NotificationEntity(UserEntity user, ProductEntity product, NotificationType type, String message, LocalDateTime date, Boolean readed) {
        this.user = user;
        this.product = product;
        this.type = type;
        this.message = message;
        this.date = date;
        this.readed = readed;
    }

    public Notification toDomainModel() {
        return new Notification(user.toDomainModel(), product.toDomainModel(), type, message, date, readed);
    }

    public static NotificationEntity fromDomainModel(Notification notification) {
        return new NotificationEntity(UserEntity.fromDomainModel(notification.getUser()),
                ProductEntity.fromDomainModel(notification.getProduct()),
                notification.getType(), notification.getMessage(), notification.getDate(), notification.getReaded());
    }
}
