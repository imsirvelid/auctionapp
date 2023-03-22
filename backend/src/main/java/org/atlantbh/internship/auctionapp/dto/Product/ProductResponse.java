package org.atlantbh.internship.auctionapp.dto.Product;

import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.atlantbh.internship.auctionapp.model.ImageEntity;
import org.atlantbh.internship.auctionapp.model.STATUS;
import org.atlantbh.internship.auctionapp.model.UserEntity;

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
    private Long userId;
    private String name;
    private String details;
    private Double startingPrice;
    private LocalDateTime created;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String thumbnailUrl;
    private List<String> images;
    @Enumerated(EnumType.STRING)
    private STATUS status;

    public ProductResponse(Long id, Long userId, String name, String details, Double startingPrice, LocalDateTime created, LocalDateTime startDate, LocalDateTime endDate, String thumbnailUrl, STATUS status) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.details = details;
        this.startingPrice = startingPrice;
        this.created = created;
        this.startDate = startDate;
        this.endDate = endDate;
        this.thumbnailUrl = thumbnailUrl;
        this.images = null;
        this.status = status;
    }
}
