package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @NotBlank
    @Column(name = "url")
    private String imageUrl;

    @NotBlank
    @Column(name = "featured")
    private Boolean featured;
}
