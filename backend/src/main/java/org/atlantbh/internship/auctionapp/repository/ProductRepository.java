package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

}
