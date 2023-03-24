package org.atlantbh.internship.auctionapp.controller;

import org.atlantbh.internship.auctionapp.entity.CategoryEntity;
import org.atlantbh.internship.auctionapp.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/parent")
    public ResponseEntity<List<CategoryEntity>> getParentCategories(){
        return ResponseEntity.ok(categoryService.getParentCategories());
    }
}
