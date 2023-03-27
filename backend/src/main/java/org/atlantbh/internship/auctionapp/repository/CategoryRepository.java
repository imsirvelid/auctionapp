package org.atlantbh.internship.auctionapp.repository;
import org.atlantbh.internship.auctionapp.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByParentCategoryIsNull();
}
