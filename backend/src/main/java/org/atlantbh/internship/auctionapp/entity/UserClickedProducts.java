package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "user_clicked_products")
public class UserClickedProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "count")
    private Integer count;

    @NotNull
    @Column(name = "date_clicked")
    private LocalDateTime dateClicked;

    public UserClickedProducts(ProductEntity product, Long userId, Integer count, LocalDateTime dateClicked) {
        this.product = product;
        this.userId = userId;
        this.count = count;
        this.dateClicked = dateClicked;
    }
}
