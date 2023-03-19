package org.atlantbh.internship.auctionapp.repository;

import org.atlantbh.internship.auctionapp.dto.Category.CategoryResponse;
import org.atlantbh.internship.auctionapp.model.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    @Query( "select new org.atlantbh.internship.auctionapp.dto.Category.CategoryResponse(c.id, c.name, c.imageUrl) from CategoryEntity c where c.parentCategory is null")
    List<CategoryResponse> getParentCategories();
}
