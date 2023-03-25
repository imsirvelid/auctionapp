package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.model.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>, PagingAndSortingRepository<ProductEntity, Long> {

    @Query("select new org.atlantbh.internship.auctionapp.dto.Product.ProductResponse(pe.id, pe.user.id, pe.name, pe.details, pe.startingPrice, pe.created, pe.startDate, pe.endDate, ie.imageUrl, pe.status) from ProductEntity pe left join ImageEntity ie on pe.id = ie.product.id where ie.featured = true")
    List<ProductResponse> getProductsWithThumbnails(Pageable pageable);

}

/*
    private Long id;
    private Long userId;
    private String name;
    private String details;
    private Double startingPrice;
    private LocalDateTime created;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String thumbnailUrl;
    private List<String> images;
    @Enumerated(EnumType.STRING)
    private STATUS status;
*/