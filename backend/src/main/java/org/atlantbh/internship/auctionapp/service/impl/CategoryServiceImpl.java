package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.model.Category.CategoryResponse;
import org.atlantbh.internship.auctionapp.repository.CategoryRepository;
import org.atlantbh.internship.auctionapp.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getParentCategories() {
        return categoryRepository.getParentCategories();
    }
}
