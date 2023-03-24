package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getParentCategories();
}
