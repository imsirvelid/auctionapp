package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.BidEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface BidRepository extends CrudRepository<BidEntity, Long> {

    Optional<BidEntity> findFirst1ByProductIdOrderByPriceDesc(Long productId);
    Integer countByProductId(Long productId);
}
