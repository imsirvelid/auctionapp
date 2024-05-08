package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.entity.NotificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByUserId(Long userId);
    List<NotificationEntity> findAllByUserIdAndReadedIsFalse(Long userId);
}
