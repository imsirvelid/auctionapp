package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.ProductEntity;
import org.atlantbh.internship.auctionapp.model.Product;
import org.atlantbh.internship.auctionapp.model.ProductResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>, PagingAndSortingRepository<ProductEntity, Long> {

    @Query("select new org.atlantbh.internship.auctionapp.model.Product(pe.id, pe.user, pe.name, pe.details, pe.startingPrice, pe.created, pe.startDate, pe.endDate, ie.imageUrl, pe.status) from ProductEntity pe left join ImageEntity ie on pe.id = ie.product.id where ie.featured = true")
    List<Product> getProductsWithThumbnails(Pageable pageable);

    @Query("select pe from ProductEntity pe order by random() limit 1")
    ProductEntity findOneRandom();

    @Query("select new org.atlantbh.internship.auctionapp.model.ProductResponse(pe.id, pe.user, pe.name, pe.details, pe.startingPrice, pe.created, pe.startDate, pe.endDate, (select image.imageUrl from ImageEntity image where i.product.id = ?1), (select count(bids) from BidEntity bid where bid.product.id = ?1), (select max(bid.price) from BidEntity bid where bid.product.id = ?1), pe.status from ProductEntity pe where pe.id = ?1")
    ProductResponse getSingleProductById(Long id);
}

/*
* this.id = id;
        this.user = user.toDomainModel();
        this.name = name;
        this.details = details;
        this.startingPrice = startingPrice;
        this.created = created;
        this.startDate = startDate;
        this.endDate = endDate;
        this.images = images;
        this.totalBids = totalBids;
        this.highestBid = highestBid;
        this.status = status;
*
* */