package org.atlantbh.internship.auctionapp.dto.Product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.atlantbh.internship.auctionapp.model.Status;

import java.time.LocalDateTime;

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
    @Enumerated(EnumType.STRING)
    private Status status;
}
