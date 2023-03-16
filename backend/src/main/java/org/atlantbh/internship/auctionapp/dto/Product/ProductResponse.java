package org.atlantbh.internship.auctionapp.dto.Product;

import lombok.*;
import org.atlantbh.internship.auctionapp.model.STATUS;
import org.atlantbh.internship.auctionapp.model.UserEntity;

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
    private LocalDateTime created;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private STATUS status;
}
