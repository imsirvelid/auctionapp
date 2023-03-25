package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "bid")
public class BidEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "price")
    private Double price;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "bid_date")
    private LocalDateTime dateOfBid;
}
