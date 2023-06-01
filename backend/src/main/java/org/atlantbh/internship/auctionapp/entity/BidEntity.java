package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    private BigDecimal price;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "bid_date")
    private LocalDateTime dateOfBid;

    public BidEntity(UserEntity user, ProductEntity product, BigDecimal price, LocalDateTime created, LocalDateTime dateOfBid) {
        this.user = user;
        this.product = product;
        this.price = price;
        this.created = created;
        this.dateOfBid = dateOfBid;
    }
}
