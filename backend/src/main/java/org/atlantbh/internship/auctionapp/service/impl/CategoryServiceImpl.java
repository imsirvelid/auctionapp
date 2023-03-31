package org.atlantbh.internship.auctionapp.service.impl;

import org.atlantbh.internship.auctionapp.entity.CategoryEntity;
import org.atlantbh.internship.auctionapp.model.Category;
import org.atlantbh.internship.auctionapp.repository.CategoryRepository;
import org.atlantbh.internship.auctionapp.service.api.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getParentCategories() {
        return categoryRepository.findAllByParentCategoryIsNull().stream().map(CategoryEntity::toDomainModel).collect(Collectors.toList());
    }
}
