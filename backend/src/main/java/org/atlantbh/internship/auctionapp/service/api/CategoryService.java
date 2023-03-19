package org.atlantbh.internship.auctionapp.service.api;

import org.atlantbh.internship.auctionapp.dto.Category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    public List<CategoryResponse> getParentCategories();
}
