package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {
    Optional<CreditCardEntity> findByUserId(Long userId);
}
