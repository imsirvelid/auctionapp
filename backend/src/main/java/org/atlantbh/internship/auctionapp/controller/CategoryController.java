package org.atlantbh.internship.auctionapp.controller;

import org.atlantbh.internship.auctionapp.dto.Category.CategoryResponse;
import org.atlantbh.internship.auctionapp.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/categories/parent")
    public ResponseEntity<List<CategoryResponse>> getParentCategories(){
        return ResponseEntity.ok(categoryService.getParentCategories());
    }
}
