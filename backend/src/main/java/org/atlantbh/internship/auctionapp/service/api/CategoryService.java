package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getParentCategories();
    List<Category> getSubCategories(Long parentCategoryId);
}
