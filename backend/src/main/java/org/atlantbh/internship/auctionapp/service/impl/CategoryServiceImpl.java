package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.CategoryEntity;
import org.atlantbh.internship.auctionapp.repository.CategoryRepository;
import org.atlantbh.internship.auctionapp.service.api.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryEntity> getParentCategories() {
        return categoryRepository.findAllByParentCategoryIsNull();
    }
}
