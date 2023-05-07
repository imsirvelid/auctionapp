package org.atlantbh.internship.auctionapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.atlantbh.internship.auctionapp.model.Product;
import org.atlantbh.internship.auctionapp.request.CreateProductRequest;

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
    @GeneratedValue(strategy = GenerationType.TABLE)
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

    @NotNull
    @Column(name = "starting_price")
    private BigDecimal startingPrice;

    @NotNull
    @Column(name = "created")
    private LocalDateTime created;

    @NotNull
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDateTime endDate;


    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ImageEntity> images;

    public Product toDomainModel(){
        return new Product(id, user.toDomainModel(), name, details, startingPrice, created, startDate, endDate, category.toDomainModel(), status, images.stream().map(ImageEntity::toDomainModelWithoutProduct).collect(Collectors.toList()));
    }

    public static ProductEntity fromDomainModel(final Product product) {
        final ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setCreated(product.getCreated());
        productEntity.setEndDate(product.getEndDate());
        productEntity.setDetails(product.getDetails());
        productEntity.setStartDate(product.getStartDate());
        productEntity.setStartingPrice(product.getStartingPrice());
        productEntity.setStatus(product.getStatus());
        productEntity.setCategory(CategoryEntity.fromDomainModel(product.getCategory()));
        productEntity.setUser(UserEntity.fromDomainModel(product.getUser()));
        return productEntity;
    }

    public static ProductEntity fromRequest(final CreateProductRequest request){
        final ProductEntity entity = new ProductEntity();
        entity.setCreated(LocalDateTime.now());
        entity.setDetails(request.getDescription());
        entity.setName(request.getProductName());
        entity.setStartingPrice(request.getStartingPrice());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        return entity;
    }
}

