package org.atlantbh.internship.auctionapp.model;

import lombok.*;
import org.atlantbh.internship.auctionapp.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private Category category;
    private Status status;
    private List<Image> images;

}
