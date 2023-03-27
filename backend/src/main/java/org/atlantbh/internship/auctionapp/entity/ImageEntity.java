package org.atlantbh.internship.auctionapp.entity;
import jakarta.persistence.*;
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

    @Column(name = "featured")
    private Boolean featured;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "url")
    private String imageUrl;
}
