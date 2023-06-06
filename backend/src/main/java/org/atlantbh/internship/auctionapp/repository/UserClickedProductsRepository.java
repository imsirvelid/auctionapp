package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.UserClickedProducts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserClickedProductsRepository extends CrudRepository<UserClickedProducts, Long> {

    Optional<UserClickedProducts> findByUserIdAndProductId(Long userId, Long productId);
}
