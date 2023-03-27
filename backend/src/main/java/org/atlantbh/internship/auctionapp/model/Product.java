package org.atlantbh.internship.auctionapp.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.atlantbh.internship.auctionapp.entity.Status;
import org.atlantbh.internship.auctionapp.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Product {
    private Long id;
    private User user;
    private String name;
    private String details;
    private BigDecimal startingPrice;
    private LocalDateTime created;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String thumbnailUrl;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Product(Long id, UserEntity userEntity, String name, String details, BigDecimal startingPrice, LocalDateTime created, LocalDateTime startDate, LocalDateTime endDate, String thumbnailUrl, Status status) {
        this.id = id;
        this.user = userEntity.toDomainModel();
        this.name = name;
        this.details = details;
        this.startingPrice = startingPrice;
        this.created = created;
        this.startDate = startDate;
        this.endDate = endDate;
        this.thumbnailUrl = thumbnailUrl;
        this.status = status;
    }
}
