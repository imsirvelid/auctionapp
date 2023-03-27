package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.atlantbh.internship.auctionapp.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "details")
    private String details;

    @NotBlank
    @Column(name = "starting_price")
    private BigDecimal startingPrice;

    @NotBlank
    @Column(name = "created")
    private LocalDateTime created;

    @NotBlank
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotBlank
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @NotBlank
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ImageEntity> images;

    public Product toDomainModel(){
        return new Product(id, user.toDomainModel(), name, details, startingPrice, created, startDate, endDate, status, images.stream().map(ImageEntity::toDomainModelWithoutProduct).collect(Collectors.toList()));
    }

    public static ProductEntity fromDomainModel(final Product product){
        final ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setCreated(product.getCreated());
        productEntity.setEndDate(product.getEndDate());
        productEntity.setDetails(product.getDetails());
        productEntity.setStartDate(product.getStartDate());
        productEntity.setStartingPrice(product.getStartingPrice());
        productEntity.setStatus(product.getStatus());
        productEntity.setUser(UserEntity.fromDomainModel(product.getUser()));
        return productEntity;
    }
}
