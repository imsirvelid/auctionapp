package org.atlantbh.internship.auctionapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlantbh.internship.auctionapp.model.Category;
import org.atlantbh.internship.auctionapp.service.api.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Category", description = "Category APIs")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/parent")
    public ResponseEntity<List<Category>> getParentCategories(){
        return ResponseEntity.ok(categoryService.getParentCategories());
    }

    @GetMapping(value = "/subcategories/{id}")
    public ResponseEntity<List<Category>> getSubCategories(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getSubCategories(id));
    }
}
