package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.BidEntity;
import org.atlantbh.internship.auctionapp.projection.BidInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface BidRepository extends CrudRepository<BidEntity, Long> {

    Optional<BidEntity> findFirst1ByProductIdOrderByPriceDesc(Long productId);
    Integer countByProductId(Long productId);
    @Query("""
            SELECT pe.name as productName, img.url as productThumbnail, pe.id as productId, pe.endDate as endDate, b.price as myPrice, 
            (SELECT MAX(bid.price) FROM  BidEntity bid WHERE bid.product.id = pe.id) as highestPrice, 
            (SELECT COUNT(bid.id) FROM BidEntity bid WHERE bid.product.id = pe.id) as numberOfBids 
            FROM ProductEntity pe, ImageEntity img, BidEntity b
            WHERE pe.id = img.product.id and img.featured = true and b.user.id = :userId and b.product.id = pe.id
            """)
    List<BidInfo> getUserBidInfo(Long userId);
}
