package org.atlantbh.internship.auctionapp.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.atlantbh.internship.auctionapp.entity.Status;
import org.atlantbh.internship.auctionapp.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class ProductResponse {
    private Long id;
    private User user;
    private String name;
    private String details;
    private BigDecimal startingPrice;
    private LocalDateTime created;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> images;
    private Integer totalBids;
    private BigDecimal highestBid;
    @Enumerated(EnumType.STRING)
    private Status status;

    public ProductResponse(Long id, UserEntity user, String name, String details, BigDecimal startingPrice, LocalDateTime created, LocalDateTime startDate, LocalDateTime endDate, List<String> images, Integer totalBids, BigDecimal highestBid, Status status) {
        this.id = id;
        this.user = user.toDomainModel();
        this.name = name;
        this.details = details;
        this.startingPrice = startingPrice;
        this.created = created;
        this.startDate = startDate;
        this.endDate = endDate;
        this.images = images;
        this.totalBids = totalBids;
        this.highestBid = highestBid;
        this.status = status;
    }
}
