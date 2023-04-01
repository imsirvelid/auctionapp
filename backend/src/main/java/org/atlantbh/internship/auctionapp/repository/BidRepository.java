package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.BidEntity;
import org.springframework.data.repository.CrudRepository;



public interface BidRepository extends CrudRepository<BidEntity, Long> {

    BidEntity findFirst1ByProductIdOrderByPriceDesc(Long productId);
    Integer countByProductId(Long productId);
}
