package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.model.BidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BidRepository extends CrudRepository<BidEntity, Long> {

    //List<BidEntity> findByProductId(Long productId);
}
