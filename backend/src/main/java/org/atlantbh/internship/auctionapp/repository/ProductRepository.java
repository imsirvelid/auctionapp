package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.dto.Product.ProductResponse;
import org.atlantbh.internship.auctionapp.model.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>, PagingAndSortingRepository<ProductEntity, Long> {

    @Query("select new org.atlantbh.internship.auctionapp.dto.Product.ProductResponse(pe.id, pe.user.id, pe.name, pe.details, pe.startingPrice, pe.created, pe.startDate, pe.endDate, ie.imageUrl, null, pe.status) from ProductEntity pe left join ImageEntity ie on pe.id = ie.product.id")
    List<ProductResponse> getProductsWithThumbnails(Pageable pageable);

}