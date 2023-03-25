package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.model.Category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    public List<CategoryResponse> getParentCategories();
}
