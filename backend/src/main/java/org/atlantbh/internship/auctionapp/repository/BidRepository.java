package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.BidEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BidRepository extends CrudRepository<BidEntity, Long> {

    List<BidEntity> findByProductId(Long productId);
}
